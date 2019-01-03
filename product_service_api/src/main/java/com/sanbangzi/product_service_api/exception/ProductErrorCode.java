package com.sanbangzi.product_service_api.exception;

import lombok.Getter;
import lombok.Setter;

public enum ProductErrorCode {

    BUSINESS_ERROR(30001, "");

    @Getter
    private Integer code;

    @Getter
    @Setter
    private String msg;

    private ProductErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
