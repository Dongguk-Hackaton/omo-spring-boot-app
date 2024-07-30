package org.omo.omospringboot.service.travel;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.omo.omospringboot.constant.CityType;
import org.omo.omospringboot.constant.ErrorCode;
import org.omo.omospringboot.constant.ProvinceType;
import org.omo.omospringboot.constant.TimeBlockType;
import org.omo.omospringboot.dto.travel.theClosetTravelGet.TheClosetTravelGetResponseDto;
import org.omo.omospringboot.dto.travel.travelScheduleSave.TravelScheduleSaveRequestDto;
import org.omo.omospringboot.dto.travel.travelScheduleSave.TravelItineraryRequestDto;
import org.omo.omospringboot.dto.travel.travelScheduleSave.VisitsRequestDto;
import org.omo.omospringboot.entity.place.Place;
import org.omo.omospringboot.entity.travel.Travel;
import org.omo.omospringboot.entity.travel.ItineraryDays;
import org.omo.omospringboot.entity.travel.UserTravel;
import org.omo.omospringboot.entity.travel.Visits;
import org.omo.omospringboot.entity.user.User;
import org.omo.omospringboot.exception.CustomErrorException;
import org.omo.omospringboot.repository.place.PlaceRepository;
import org.omo.omospringboot.repository.travel.TravelRepository;
import org.omo.omospringboot.repository.travel.ItineraryRepository;
import org.omo.omospringboot.repository.travel.VisitsRepository;
import org.omo.omospringboot.repository.travel.UserTravelRepository;
import org.omo.omospringboot.repository.user.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TravelScheduleService {
    private final UserRepository userRepository;
    private final UserTravelRepository userTravelRepository;
    private final TravelRepository travelRepository;
    private final ItineraryRepository itineraryRepository;
    private final VisitsRepository visitsRepository;
    private final PlaceRepository placeRepository;

    public void saveTravelSchedule(User me, TravelScheduleSaveRequestDto requestDto) {
        if(me == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }
        Travel newTravel = saveTravel(me, requestDto);
        saveUserTravel(me, newTravel, requestDto);
        saveItinerary(newTravel, requestDto);
    }

    public TheClosetTravelGetResponseDto getTheClosetTravelSchedule(User user){
        if(user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        Pageable pageable = PageRequest.of(0, 1);
        List<Travel> travels = travelRepository.findTopByUserOrderByStartDayAsc(user, pageable);

        if (travels.isEmpty()) {
            throw new CustomErrorException(ErrorCode.NoClosestTravelScheduleError);
        }

        return TheClosetTravelGetResponseDto.fromDto(travels.get(0));
    }

    private Travel saveTravel(User me, TravelScheduleSaveRequestDto requestDto) {
        ProvinceType provinceType = ProvinceType.getProvinceType(requestDto.getProvince());
        CityType cityType = CityType.getCityType(requestDto.getCity());

        if(requestDto.getStartDay().isAfter(requestDto.getEndDay())) {
            throw new CustomErrorException(ErrorCode.InvalidTravelTimeError);
        }

        return travelRepository.save(Travel.of(requestDto, provinceType, cityType));
    }

    private void saveUserTravel(User me, Travel travel, TravelScheduleSaveRequestDto requestDto){
        if(requestDto.getFriendId() != null){
            User friend = userRepository.findById(requestDto.getFriendId()).orElseThrow(() -> new CustomErrorException(ErrorCode.FriendNotFoundError));
            // TODO: 친구가 맞는지 확인하는 로직필요
            userTravelRepository.save(UserTravel.of(me, travel));
            userTravelRepository.save(UserTravel.of(friend, travel));
        }else{
            userTravelRepository.save(UserTravel.of(me, travel));
        }
    }

    private void saveItinerary(Travel travel, TravelScheduleSaveRequestDto requestDto) {
        // 여행일수와 스케줄의 갯수가 맞는지 확인
        long daysBetween = ChronoUnit.DAYS.between(requestDto.getStartDay(), requestDto.getEndDay());
        if(daysBetween + 1 != requestDto.getItineraries().size()){
            throw new CustomErrorException(ErrorCode.MismatchedItineraryWithDurationError);
        }


        for(int scheduleDay = 0; scheduleDay < requestDto.getItineraries().size(); scheduleDay++){
            TravelItineraryRequestDto travelItineraryRequestDto = requestDto.getItineraries().get(scheduleDay);
            if (travelItineraryRequestDto.getStartTime().isAfter(travelItineraryRequestDto.getEndTime())) {
                throw new CustomErrorException(ErrorCode.InvalidItineraryTimeError);
            }

            if (!travelItineraryRequestDto.getStartTime().toLocalDate().equals(travelItineraryRequestDto.getEndTime().toLocalDate())) {
                throw new CustomErrorException(ErrorCode.InvalidItineraryTimeError);
            }

            if(!travelItineraryRequestDto.getStartTime().toLocalDate().equals(requestDto.getStartDay().plusDays(scheduleDay))){
                throw new CustomErrorException(ErrorCode.InvalidItineraryTimeError);
            }

            // 방문장소가 하나일 경우
            if(!travelItineraryRequestDto.getVisits().get(0).getStartTime().equals(travelItineraryRequestDto.getStartTime())) {
                throw new CustomErrorException(ErrorCode.InvalidVisitTimeError);
            }
            if(!travelItineraryRequestDto.getVisits().get(travelItineraryRequestDto.getVisits().size() - 1).getEndTime().equals( travelItineraryRequestDto.getEndTime())) {
                throw new CustomErrorException(ErrorCode.InvalidVisitTimeError);
            }

            // 방문장소가 2개 이상일 경우
            for(int locationIndex = 0; locationIndex < travelItineraryRequestDto.getVisits().size() - 1; locationIndex++){
                VisitsRequestDto currentTravelVisitLocation = travelItineraryRequestDto.getVisits().get(locationIndex);
                VisitsRequestDto nextTravelVisitLocation = travelItineraryRequestDto.getVisits().get(locationIndex + 1);

                if(currentTravelVisitLocation.getStartTime().isAfter(currentTravelVisitLocation.getEndTime())) {
                    throw new CustomErrorException(ErrorCode.InvalidVisitTimeError);
                }

                // 한 방문장소에 30분이상 머무르는지 확인
                if(currentTravelVisitLocation.getStartTime().plusMinutes(30).isAfter(currentTravelVisitLocation.getEndTime())) {
                    throw new CustomErrorException(ErrorCode.TooShortVisitDurationError);
                }

                // 현재장소를 떠나는 시간이 다음장소에 도착하는 시간과 같은지 확인
                if(!currentTravelVisitLocation.getEndTime().equals(nextTravelVisitLocation.getStartTime())){
                    throw new CustomErrorException(ErrorCode.InvalidVisitTimeError);
                }
            }

            ItineraryDays newTravelSchedule = itineraryRepository.save(ItineraryDays.of(travel, travelItineraryRequestDto));
            saveVisits(travelItineraryRequestDto, newTravelSchedule);
        }
    }

    private void saveVisits(TravelItineraryRequestDto travelItineraryRequestDto, ItineraryDays travelSchedule) {
        for(int locationIndex = 0; locationIndex < travelItineraryRequestDto.getVisits().size(); locationIndex++){
            VisitsRequestDto travelVisitLocationDto = travelItineraryRequestDto.getVisits().get(locationIndex);

            TimeBlockType timeBlockType = TimeBlockType.getTimeBlockType(travelVisitLocationDto.getTimeBlockType());
            Place place = placeRepository.findById(travelVisitLocationDto.getPlaceId())
                    .orElseThrow(() -> new CustomErrorException(ErrorCode.NoSuchPlaceError));

            visitsRepository.save(Visits.of(travelSchedule, timeBlockType, place, travelVisitLocationDto));
        }
    }


}
