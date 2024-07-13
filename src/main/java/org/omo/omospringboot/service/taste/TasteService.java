package org.omo.omospringboot.service.taste;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.omo.omospringboot.constant.ErrorCode;
import org.omo.omospringboot.dto.taste.TasteGetResponseDto;
import org.omo.omospringboot.dto.taste.TasteSaveRequestDto;
import org.omo.omospringboot.entity.*;
import org.omo.omospringboot.exception.CustomErrorException;
import org.omo.omospringboot.repository.*;
import org.springframework.stereotype.Service;

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

        if(tasteProfileRepository.findByUser(user).isEmpty()) {
            throw new CustomErrorException(ErrorCode.TasteProfileNotFoundError);
        }

        TasteProfile tasteProfile = tasteProfileRepository.findByUserId(user.getId());
        Long tasteProfileId = tasteProfile.getId();

        List<DislikedFood> dislikedFoodList = dislikedFoodRepository.findByTasteProfileId(tasteProfileId);
        List<String> dislikedFoods = dislikedFoodList.stream()
                .map(DislikedFood::getFoodName)
                .collect(Collectors.toList());

        List<FavoriteFood> favoriteFoodList = favoriteFoodRepository.findByTasteProfileId(tasteProfileId);
        List<String> favoriteFoods = favoriteFoodList.stream()
                .map(FavoriteFood::getFoodName)
                .collect(Collectors.toList());

        List<String> interestCodes = tasteProfile.getInterests().stream()
                .map(Interest::getInterestCode)
                .collect(Collectors.toList());

        return TasteGetResponseDto.builder()
                .userActivity(tasteProfile.getUserActivity())
                .userWalking(tasteProfile.getUserWalking())
                .favoriteFoods(favoriteFoods)
                .dislikedFoods(dislikedFoods)
                .interestCode(interestCodes)
                .build();
    }

    public void patchTaste(User user, TasteSaveRequestDto requestDto) {
        if(user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        TasteProfile tasteProfile = tasteProfileRepository.findByUserId(user.getId());
        Long tasteProfileId = tasteProfile.getId();

        tasteProfile.setUserActivity(requestDto.getUserActivity());
        tasteProfile.setUserWalking(requestDto.getUserWalking());

        dislikedFoodRepository.deleteByTasteProfileId(tasteProfileId);
        List<String> dislikedFoods = requestDto.getDislikedFoods();
        dislikedFoods.forEach(foodName -> {
            DislikedFood dislikedFood = DislikedFood.of(tasteProfile, foodName);
            dislikedFoodRepository.save(dislikedFood);
        });

        favoriteFoodRepository.deleteByTasteProfileId(tasteProfileId);
        List<String> favoriteFoods = requestDto.getFavoriteFoods();
        favoriteFoods.forEach(foodName -> {
            FavoriteFood favoriteFood = FavoriteFood.of(tasteProfile, foodName);
            favoriteFoodRepository.save(favoriteFood);
        });


        tasteProfile.getInterests().clear();
        List<String> interestCodes = requestDto.getInterestCode();

        for (String interestCode : interestCodes) {
            Interest interest = interestRepository.findByInterestCode(interestCode)
                    .orElseThrow(() -> new CustomErrorException(ErrorCode.NotExistInterestCodeError));
            tasteProfile.getInterests().add(interest);
        }

        tasteProfileRepository.save(tasteProfile);
    }

    public void deleteTaste(User user) {
        TasteProfile tasteProfile = tasteProfileRepository.findByUserId(user.getId());
        Long tasteProfileId = tasteProfile.getId();

        dislikedFoodRepository.deleteByTasteProfileId(tasteProfileId);
        favoriteFoodRepository.deleteByTasteProfileId(tasteProfileId);
        tasteProfile.getInterests().clear();
        tasteProfileRepository.delete(tasteProfile);
    }
}
