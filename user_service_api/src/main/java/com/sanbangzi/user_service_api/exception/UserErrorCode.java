package com.sanbangzi.user_service_api.exception;

import lombok.Getter;
import lombok.Setter;

public enum UserErrorCode {

    BUSINESS_ERROR(20001, ""),
    LOGIN_ERROR(20002, "登录失败"),
    REGISTER_ERROR(20003, "注册失败");

    @Getter
    private Integer code;

    @Getter
    @Setter
    private String msg;

    private UserErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
