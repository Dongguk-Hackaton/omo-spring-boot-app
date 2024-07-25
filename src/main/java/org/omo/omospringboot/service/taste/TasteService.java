package org.omo.omospringboot.service.taste;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.omo.omospringboot.constant.ErrorCode;
import org.omo.omospringboot.constant.FoodType;
import org.omo.omospringboot.constant.InterestType;
import org.omo.omospringboot.dto.taste.*;
import org.omo.omospringboot.entity.*;
import org.omo.omospringboot.exception.CustomErrorException;
import org.omo.omospringboot.repository.DislikedFoodRepository;
import org.omo.omospringboot.repository.FavoriteFoodRepository;
import org.omo.omospringboot.repository.InterestRepository;
import org.omo.omospringboot.repository.TasteProfileRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        TasteProfile newTasteProfile = tasteProfileRepository.save(TasteProfile.of(
                user,
                requestDto.getUserActivity()
        ));

        requestDto.getInterests().forEach(
                s -> {
                        InterestType interestType = InterestType.getInterestType(s);
                        interestRepository.save(Interest.of(newTasteProfile, interestType));
                }
        );

        requestDto.getFavoriteFoods().forEach(
                s -> {
                        FoodType foodType = FoodType.getFoodType(s);
                        favoriteFoodRepository.save(FavoriteFood.of(newTasteProfile, foodType));
                }
        );

        requestDto.getDislikedFoods().forEach(
                s -> {
                        FoodType foodType = FoodType.getFoodType(s);
                        dislikedFoodRepository.save(DislikedFood.of(newTasteProfile, foodType));
                }
        );

    }

    public TasteGetResponseDto getTaste(User user) {
        if(user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        TasteProfile tasteProfile = tasteProfileRepository.findByUser(user)
                .orElseThrow(() -> new CustomErrorException(ErrorCode.TasteProfileNotFoundError));

        List<DislikedFood> dislikedFoodList = dislikedFoodRepository.findByTasteProfile(tasteProfile);
        List<FoodType> dislikedFoods = getDislikedFoods(dislikedFoodList);

        List<FavoriteFood> favoriteFoodList = favoriteFoodRepository.findByTasteProfile(tasteProfile);
        List<FoodType> favoriteFoods = getFavoriteFoods(favoriteFoodList);

        List<Interest> interestList = interestRepository.findByTasteProfile(tasteProfile);
        List<InterestType> interests = getInterests(interestList);

        return TasteGetResponseDto.builder()
                .userActivity(tasteProfile.getUserActivity())
                .favoriteFoods(favoriteFoods)
                .dislikedFoods(dislikedFoods)
                .interests(interests)
                .build();
    }

    private static List<FoodType> getDislikedFoods(List<DislikedFood> dislikedFoodList) {
        return dislikedFoodList.stream()
                .map(DislikedFood::getFoodType)
                .collect(Collectors.toList());
    }

    private static List<FoodType> getFavoriteFoods(List<FavoriteFood> favoriteFoodList) {
        return favoriteFoodList.stream()
                .map(FavoriteFood::getFoodType)
                .collect(Collectors.toList());
    }

    private static List<InterestType> getInterests(List<Interest> interests) {
        return interests.stream()
                .map(Interest::getInterestType)
                .collect(Collectors.toList());
    }

    public TasteUpdateResponseDto putTaste(User user, TasteUpdateRequestDto requestDto) {
        if(user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        TasteProfile tasteProfile = tasteProfileRepository.findByUser(user)
                .orElseThrow(() -> new CustomErrorException(ErrorCode.TasteProfileNotFoundError));

        tasteProfile.update(user, requestDto.getUserActivity());

        favoriteFoodRepository.deleteByTasteProfile(tasteProfile);
        requestDto.getFavoriteFoods().forEach(
                s -> {
                        FoodType foodType = FoodType.getFoodType(s);
                        favoriteFoodRepository.save(FavoriteFood.of(tasteProfile, foodType));
                }
        );

        dislikedFoodRepository.deleteByTasteProfile(tasteProfile);
        requestDto.getDislikedFoods().forEach(
                s -> {
                        FoodType foodType = FoodType.getFoodType(s);
                        dislikedFoodRepository.save(DislikedFood.of(tasteProfile, foodType));
                }
        );

        interestRepository.deleteByTasteProfile(tasteProfile);
        requestDto.getInterests().forEach(
                s -> {
                        InterestType interestType = InterestType.getInterestType(s);
                        interestRepository.save(Interest.of(tasteProfile, interestType));
                }
        );

        return TasteUpdateResponseDto.builder()
                .updateTime(LocalDateTime.now())
                .tasteProfileId(tasteProfile.getId())
                .message("취향이 수정되었습니다.")
                .build();
    }

    public TasteDeleteResponseDto deleteTaste(User user) {
        if(user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        TasteProfile tasteProfile = tasteProfileRepository.findByUser(user)
                .orElseThrow(() -> new CustomErrorException(ErrorCode.TasteProfileNotFoundError));

        dislikedFoodRepository.deleteByTasteProfile(tasteProfile);
        favoriteFoodRepository.deleteByTasteProfile(tasteProfile);
        interestRepository.deleteByTasteProfile(tasteProfile);
        tasteProfileRepository.delete(tasteProfile);

        return TasteDeleteResponseDto.builder()
                .deleteTime(LocalDateTime.now())
                .tasteProfileId(tasteProfile.getId())
                .message("취향이 삭제되었습니다.")
                .build();
    }
}
