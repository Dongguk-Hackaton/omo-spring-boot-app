package org.omo.omospringboot.constant;

import lombok.Getter;
import org.omo.omospringboot.exception.CustomErrorException;

@Getter
public enum TimeBlockType {
    DYNAMIC,      // 다이나믹
    PLAY,         // 놀기
    ALCOHOL,      // 술
    WATCH,        // 보기
    WALK,         // 걷기
    DRINK,        // 마시기
    EAT,          // 먹기
    EXPERIENCE;   // 경험

    public static TimeBlockType getTimeBlockType(String foodTypeStr) throws CustomErrorException {
        for (TimeBlockType timeBlockType : TimeBlockType.values()) {
            if (timeBlockType.name().equals(foodTypeStr)) return timeBlockType;
        }

        throw new CustomErrorException(ErrorCode.NoSuchTimeBlockError);
    }
}
