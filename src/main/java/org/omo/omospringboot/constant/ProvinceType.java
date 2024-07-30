package org.omo.omospringboot.constant;

import org.omo.omospringboot.exception.CustomErrorException;

public enum ProvinceType {
    GYEONGSANGBUKDO;

    public static ProvinceType getProvinceType(String provinceTypeStr) throws CustomErrorException {
        for (ProvinceType provinceType : ProvinceType.values()) {
            if (provinceType.name().equals(provinceTypeStr)) return provinceType;
        }

        throw new CustomErrorException(ErrorCode.NoSuchProvinceTypeError);
    }
}
