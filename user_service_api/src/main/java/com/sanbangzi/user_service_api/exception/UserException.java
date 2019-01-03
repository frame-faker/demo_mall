package com.sanbangzi.user_service_api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserException extends RuntimeException {

    @Getter
    private UserErrorCode userErrorCode;

    public static UserException throwErrorCode(UserErrorCode userErrorCode) {
        return new UserException(userErrorCode);
    }

    public static UserException throwBusiError(String msg) {
        UserErrorCode.BUSINESS_ERROR.setMsg(msg);
        return new UserException(UserErrorCode.BUSINESS_ERROR);
    }
}
