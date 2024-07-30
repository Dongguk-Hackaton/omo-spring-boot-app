package org.omo.omospringboot.constant;

import org.omo.omospringboot.exception.CustomErrorException;

public enum CityType {
    GYEONGJU;

    public static CityType getCityType(String cityTypeStr) throws CustomErrorException {
        for (CityType cityType : CityType.values()) {
            if (cityType.name().equals(cityTypeStr)) return cityType;
        }

        throw new CustomErrorException(ErrorCode.NoSuchCityTypeError);
    }
}
