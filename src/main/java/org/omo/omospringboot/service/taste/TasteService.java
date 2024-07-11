package org.omo.omospringboot.service.taste;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.omo.omospringboot.constant.ErrorCode;
import org.omo.omospringboot.dto.taste.TasteSaveRequestDto;
import org.omo.omospringboot.dto.taste.TasteSaveResponseDto;
import org.omo.omospringboot.entity.*;
import org.omo.omospringboot.exception.CustomErrorException;
import org.omo.omospringboot.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
