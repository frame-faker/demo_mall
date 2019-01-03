package com.sanbangzi.xcx_web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class XcxException extends RuntimeException {

    @Getter
    private XcxErrorCode xcxErrorCode;

    public static XcxException throwErrorCode(XcxErrorCode xcxErrorCode) {
        return new XcxException(xcxErrorCode);
    }

    public static XcxException throwParamNull(String paramName) {
        XcxErrorCode.PARAM_NULL_ERROR.setMsg("参数[" + paramName + "] 为空");
        return new XcxException(XcxErrorCode.PARAM_NULL_ERROR);
    }
}
