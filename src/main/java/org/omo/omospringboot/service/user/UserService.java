package org.omo.omospringboot.service.user;


import lombok.RequiredArgsConstructor;
import org.omo.omospringboot.constant.ErrorCode;
import org.omo.omospringboot.dto.user.UserInfoResponseDto;
import org.omo.omospringboot.entity.user.User;
import org.omo.omospringboot.exception.CustomErrorException;
import org.omo.omospringboot.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfoResponseDto getUserInfo(User user) {
        if(user == null) {
            throw new CustomErrorException(ErrorCode.UserNotFoundError);
        }

        return UserInfoResponseDto.fromEntity(user);
    }
}
