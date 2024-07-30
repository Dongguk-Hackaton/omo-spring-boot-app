package org.omo.omospringboot.service.travel;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.omo.omospringboot.constant.ErrorCode;
import org.omo.omospringboot.dto.place.PlaceGet;
import org.omo.omospringboot.entity.place.*;
import org.omo.omospringboot.entity.user.User;
import org.omo.omospringboot.exception.CustomErrorException;
import org.omo.omospringboot.repository.place.PlaceRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaceService {
    private final PlaceRepository placeRepository;
    public PlaceGet.Response getPlace(User user, Long placeId){
        if (user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        Place place = placeRepository.findById(placeId).orElseThrow(() -> new CustomErrorException(ErrorCode.NoSuchPlaceError));

        if(place instanceof CultureFacility){
            return PlaceGet.Response.fromEntity((CultureFacility) place);
        }

        if(place instanceof Leisure){
            return PlaceGet.Response.fromEntity((Leisure) place);
        }

        if(place instanceof Restaurant){
            return PlaceGet.Response.fromEntity((Restaurant) place);
        }

        if(place instanceof TouristSpot){
            return PlaceGet.Response.fromEntity((TouristSpot) place);
        }

        if(place instanceof UniqueExperience){
            return PlaceGet.Response.fromEntity((UniqueExperience) place);
        }

        throw new CustomErrorException(ErrorCode.NoSuchPlaceError);
    }
}
