package com.sanbangzi.product_service_api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ProductException extends RuntimeException {

    @Getter
    private ProductErrorCode productErrorCode;

    public static ProductException throwBusiError(String msg) {
        ProductErrorCode.BUSINESS_ERROR.setMsg(msg);
        return new ProductException(ProductErrorCode.BUSINESS_ERROR);
    }
}
