package com.sanbangzi.xcx_web.exception;

import lombok.Getter;
import lombok.Setter;

public enum XcxErrorCode {

    SIGN_ERROR(10001, "签名错误"),
    ACCESS_TOKEN_ERROR(10002, "登录token错误"),
    PARAM_NULL_ERROR(10003, ""),
    DATA_CHECK_ERROR(10004, "暂不支持该种传输校验");

    @Getter
    private Integer code;

    @Setter
    @Getter
    private String msg;

    private XcxErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
