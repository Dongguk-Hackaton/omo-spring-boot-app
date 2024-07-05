package org.omo.omospringboot.exception;

import lombok.Getter;
import org.omo.omospringboot.constant.ErrorCode;

@Getter
public class CustomErrorException extends RuntimeException{
    private final ErrorCode errorCode;

    public CustomErrorException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
