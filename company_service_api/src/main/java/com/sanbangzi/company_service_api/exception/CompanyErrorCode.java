package com.sanbangzi.company_service_api.exception;

import lombok.Getter;
import lombok.Setter;

public enum CompanyErrorCode {

    BUSINESS_ERROR(40001, "");

    @Getter
    private Integer code;

    @Getter
    @Setter
    private String msg;

    private CompanyErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
