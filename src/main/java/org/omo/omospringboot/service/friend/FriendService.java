package org.omo.omospringboot.service.friend;

import lombok.RequiredArgsConstructor;
import org.omo.omospringboot.constant.*;
import org.omo.omospringboot.dto.friend.*;
import org.omo.omospringboot.entity.taste.*;
import org.omo.omospringboot.entity.user.FriendShip;
import org.omo.omospringboot.entity.user.User;
import org.omo.omospringboot.exception.CustomErrorException;
import org.omo.omospringboot.repository.friend.FriendRepository;
import org.omo.omospringboot.repository.taste.*;
import org.omo.omospringboot.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final DateStyleRepository dateStyleRepository;
    private final InterestRepository interestRepository;
    private final DislikedFoodRepository dislikedFoodRepository;
    private final FavoriteFoodRepository favoriteFoodRepository;
    private final TasteProfileRepository tasteProfileRepository;

    public void saveFriend(User user, FriendSaveRequestDto requestDto) {

        if(user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        if (user.getId().equals(requestDto.getFriendId())) {
            throw new CustomErrorException(ErrorCode.SelfAddError);
        }

        User friend = userRepository.findById(requestDto.getFriendId())
                .orElseThrow(() -> new CustomErrorException(ErrorCode.FriendNotFoundError));

        if (friendRepository.existsByUserAndFriend(user, friend)) {
            throw new CustomErrorException(ErrorCode.AlreadyExistFriend);
        }

        friendRepository.save(FriendShip.of(user, friend));
        friendRepository.save(FriendShip.of(friend, user));
    }

    public List<FriendGetResponseDto> getFriend(User user) {

        if(user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        List<FriendShip> friendships = friendRepository.findAllByUser(user);

        return friendships.stream()
                .map(s -> {
                    User friend = s.getFriend();
                    return FriendGetResponseDto.builder()
                            .friendId(friend.getId())
                            .friendNickname(friend.getNickname())
                            .friendProfileImage(friend.getProfileImage())
                            .build();
                })
                .collect(Collectors.toList());
    }

    public FriendDeleteResponseDto deleteFriend(User user, Long friendId) {

        if(user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new CustomErrorException(ErrorCode.UserNotFoundError));

        FriendShip friendship1 = friendRepository.findByUserAndFriend(user, friend)
                .orElseThrow(() -> new CustomErrorException(ErrorCode.FriendNotFoundError));

        FriendShip friendship2 = friendRepository.findByUserAndFriend(friend, user)
                .orElseThrow(() -> new CustomErrorException(ErrorCode.FriendNotFoundError));

        friendRepository.delete(friendship1);
        friendRepository.delete(friendship2);

        return FriendDeleteResponseDto.builder()
                .message("친구가 삭제되었습니다.")
                .friendId(friendId)
                .deletedTime(LocalDateTime.now())
                .build();
    }

    public FriendDetailGetResponseDto detailFriend(User user, Long friendId) {

        if(user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new CustomErrorException(ErrorCode.FriendNotFoundError));

        TasteProfile friendTasteProfile = tasteProfileRepository.findByUser(friend)
                .orElseThrow(() -> new CustomErrorException(ErrorCode.TasteProfileNotFoundError));

        List<DateStyle> dateStyleList = dateStyleRepository.findByTasteProfile(friendTasteProfile);
        List<DateStyleType> dateStyles = getDateStyles(dateStyleList);

        List<DislikedFood> dislikedFoodList = dislikedFoodRepository.findByTasteProfile(friendTasteProfile);
        List<FoodType> dislikedFoods = getDislikedFoods(dislikedFoodList);

        List<FavoriteFood> favoriteFoodList = favoriteFoodRepository.findByTasteProfile(friendTasteProfile);
        List<FoodType> favoriteFoods = getFavoriteFoods(favoriteFoodList);

        List<Interest> interestList = interestRepository.findByTasteProfile(friendTasteProfile);
        List<InterestType> interests = getInterests(interestList);

        return FriendDetailGetResponseDto.builder()
                .friendId(friend.getId())
                .friendNickname(friend.getNickname())
                .friendProfileImage(friend.getProfileImage())
                .friendActivity(friendTasteProfile.getUserActivity())
                .friendInterests(interests)
                .friendDateStyles(dateStyles)
                .friendDislikedFoods(dislikedFoods)
                .friendFavoriteFoods(favoriteFoods)
                .build();
    }

    private static List<DateStyleType> getDateStyles(List<DateStyle> dateStyleList) {
        return dateStyleList.stream()
                .map(DateStyle::getDateStyleType)
                .collect(Collectors.toList());
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
}
