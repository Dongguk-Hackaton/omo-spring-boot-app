package org.omo.omospringboot.service.taste;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.omo.omospringboot.constant.ErrorCode;
import org.omo.omospringboot.dto.taste.TasteDeleteResponseDto;
import org.omo.omospringboot.dto.taste.TasteGetResponseDto;
import org.omo.omospringboot.dto.taste.TastePutResponseDto;
import org.omo.omospringboot.dto.taste.TasteSaveRequestDto;
import org.omo.omospringboot.entity.*;
import org.omo.omospringboot.exception.CustomErrorException;
import org.omo.omospringboot.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TasteService {
    private final TasteProfileRepository tasteProfileRepository;
    private final InterestRepository interestRepository;
    private final DislikedFoodRepository dislikedFoodRepository;
    private final FavoriteFoodRepository favoriteFoodRepository;

    public void saveTaste(User user, TasteSaveRequestDto requestDto) {
        if(user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        if(tasteProfileRepository.findByUser(user).isPresent()) {
            throw new CustomErrorException(ErrorCode.AlreadyExistTasteProfileError);
        }

        List<Interest> interests = requestDto.getInterestCode().stream().map(
                s -> interestRepository.findByInterestCode(s)
                        .orElseThrow(() -> new CustomErrorException(ErrorCode.NotExistInterestCodeError))
        ).toList();

        TasteProfile newTasteProfile = tasteProfileRepository.save(TasteProfile.of(
                user,
                requestDto.getUserActivity(),
                requestDto.getUserWalking(),
                interests
        ));

        requestDto.getFavoriteFoods().forEach(
                s -> favoriteFoodRepository.save(FavoriteFood.of(newTasteProfile, s))
        );

        requestDto.getDislikedFoods().forEach(
                s -> dislikedFoodRepository.save(DislikedFood.of(newTasteProfile, s))
        );
    }

    public TasteGetResponseDto getTaste(User user) {
        if(user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        TasteProfile tasteProfile = tasteProfileRepository.findByUser(user)
                .orElseThrow(() -> new CustomErrorException(ErrorCode.TasteProfileNotFoundError));

        List<DislikedFood> dislikedFoodList = dislikedFoodRepository.findByTasteProfile(tasteProfile);
        List<String> dislikedFoods = getDislikedFoods(dislikedFoodList);

        List<FavoriteFood> favoriteFoodList = favoriteFoodRepository.findByTasteProfile(tasteProfile);
        List<String> favoriteFoods = getFavoriteFoods(favoriteFoodList);

        List<String> interestCodes = getInterestCodes(tasteProfile);

        return TasteGetResponseDto.builder()
                .userActivity(tasteProfile.getUserActivity())
                .userWalking(tasteProfile.getUserWalking())
                .favoriteFoods(favoriteFoods)
                .dislikedFoods(dislikedFoods)
                .interestCode(interestCodes)
                .build();
    }

    private static List<String> getDislikedFoods(List<DislikedFood> dislikedFoodList) {
        return dislikedFoodList.stream()
                .map(DislikedFood::getFoodName)
                .collect(Collectors.toList());
    }

    private static List<String> getFavoriteFoods(List<FavoriteFood> favoriteFoodList) {
        return favoriteFoodList.stream()
                .map(FavoriteFood::getFoodName)
                .collect(Collectors.toList());
    }

    private static List<String> getInterestCodes(TasteProfile tasteProfile) {
        return tasteProfile.getInterests().stream()
                .map(Interest::getInterestCode)
                .collect(Collectors.toList());
    }

    public TastePutResponseDto putTaste(User user, TasteSaveRequestDto requestDto) {
        if(user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        TasteProfile tasteProfile = tasteProfileRepository.findByUser(user)
                .orElseThrow(() -> new CustomErrorException(ErrorCode.TasteProfileNotFoundError));

        List<Interest> interests = requestDto.getInterestCode().stream().map(
                s -> interestRepository.findByInterestCode(s)
                        .orElseThrow(() -> new CustomErrorException(ErrorCode.NotExistInterestCodeError))
        ).toList();


        tasteProfile.update(user, requestDto.getUserActivity(), requestDto.getUserWalking(), interests);

        favoriteFoodRepository.deleteByTasteProfile(tasteProfile);
        requestDto.getFavoriteFoods().forEach(
                s -> favoriteFoodRepository.save(FavoriteFood.of(tasteProfile, s))
        );

        dislikedFoodRepository.deleteByTasteProfile(tasteProfile);
        requestDto.getDislikedFoods().forEach(
                s -> dislikedFoodRepository.save(DislikedFood.of(tasteProfile, s))
        );

        return TastePutResponseDto.builder()
                .updateTime(LocalDateTime.now())
                .tasteProfileId(tasteProfile.getId())
                .message("취향이 수정되었습니다.")
                .build();
    }

    public TasteDeleteResponseDto deleteTaste(User user) {

        TasteProfile tasteProfile = tasteProfileRepository.findByUser(user)
                .orElseThrow(() -> new CustomErrorException(ErrorCode.TasteProfileNotFoundError));

        dislikedFoodRepository.deleteByTasteProfile(tasteProfile);
        favoriteFoodRepository.deleteByTasteProfile(tasteProfile);
        tasteProfile.getInterests().clear();
        tasteProfileRepository.delete(tasteProfile);

        return TasteDeleteResponseDto.builder()
                .deleteTime(LocalDateTime.now())
                .tasteProfileId(tasteProfile.getId())
                .message("취향이 삭제되었습니다.")
                .build();
    }
}
