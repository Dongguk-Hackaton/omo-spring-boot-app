package org.omo.omospringboot.constant;

import lombok.Getter;
import org.omo.omospringboot.exception.CustomErrorException;

@Getter
public enum InterestType {
    VALUE_FOR_MONEY,        // 가성비
    PERFORMANCE,            // 공연
    ROMANTIC,               // 로맨틱
    EATING,                 // 먹기
    NIGHT_VIEW_SCENERY,     // 야경/풍경
    ENTERTAINMENT,          // 오락
    INTIMATE,               // 오붓한
    UNUSUAL,                // 이색
    ACTIVITY,               // 활동
    HEALING;                // 힐링

    public static InterestType getInterestType(String interestTypeStr) throws CustomErrorException {
        for (InterestType interestType : InterestType.values()) {
            if (interestType.name().equals(interestTypeStr)) return interestType;
        }

        throw new CustomErrorException(ErrorCode.NoSuchInterestTypeError);
    }
}
