package com.sanbangzi.company_service_api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CompanyException extends RuntimeException {

    @Getter
    private CompanyErrorCode companyErrorCode;

    public static CompanyException throwBusiError(String msg) {
        CompanyErrorCode.BUSINESS_ERROR.setMsg(msg);
        return new CompanyException(CompanyErrorCode.BUSINESS_ERROR);
    }
}
