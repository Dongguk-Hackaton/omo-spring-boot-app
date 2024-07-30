package org.omo.omospringboot.constant;

import org.omo.omospringboot.exception.CustomErrorException;

public enum PlaceType {
    CULTURE_FACILITY,
    LEISURE,
    RESTAURANT,
    TOURIST_SPOT,
    UNIQUE_EXPERIENCE;

    public static PlaceType getPlaceType(String placeTypeStr) throws CustomErrorException {
        for (PlaceType placeType : PlaceType.values()) {
            if (placeType.name().equals(placeTypeStr)) return placeType;
        }

        throw new CustomErrorException(ErrorCode.NoSuchPlaceTypeError);
    }
}
