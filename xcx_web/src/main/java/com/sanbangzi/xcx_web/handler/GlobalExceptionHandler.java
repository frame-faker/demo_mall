package com.sanbangzi.xcx_web.handler;

import com.sanbangzi.common.utils.WebResponse;
import com.sanbangzi.company_service_api.exception.CompanyErrorCode;
import com.sanbangzi.company_service_api.exception.CompanyException;
import com.sanbangzi.product_service_api.exception.ProductErrorCode;
import com.sanbangzi.product_service_api.exception.ProductException;
import com.sanbangzi.user_service_api.exception.UserErrorCode;
import com.sanbangzi.user_service_api.exception.UserException;
import com.sanbangzi.xcx_web.exception.XcxErrorCode;
import com.sanbangzi.xcx_web.exception.XcxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(XcxException.class)
    public WebResponse xcxExceptionHandle(XcxException e) {
        XcxErrorCode xcxErrorCode = e.getXcxErrorCode();
        Integer code = xcxErrorCode.getCode();
        String msg = xcxErrorCode.getMsg();
        LOGGER.error("模板模块, 错误信息 => code:{}, msg:{}",code, msg);
        return new WebResponse(code, msg, null);
    }

    @ExceptionHandler(UserException.class)
    public WebResponse userExceptionHandle(UserException e) {
        UserErrorCode userErrorCode = e.getUserErrorCode();
        Integer code = userErrorCode.getCode();
        String msg = userErrorCode.getMsg();
        LOGGER.error("模板模块, 错误信息 => code:{}, msg:{}",code, msg);
        return new WebResponse(code, msg, null);
    }

    @ExceptionHandler(ProductException.class)
    public WebResponse productExceptionHandle(ProductException e) {
        ProductErrorCode productErrorCode = e.getProductErrorCode();
        Integer code = productErrorCode.getCode();
        String msg = productErrorCode.getMsg();
        LOGGER.error("模板模块, 错误信息 => code:{}, msg:{}",code, msg);
        return new WebResponse(code, msg, null);
    }

    @ExceptionHandler(CompanyException.class)
    public WebResponse companyExceptionHandle(CompanyException e) {
        CompanyErrorCode companyErrorCode = e.getCompanyErrorCode();
        Integer code = companyErrorCode.getCode();
        String msg = companyErrorCode.getMsg();
        LOGGER.error("模板模块, 错误信息 => code:{}, msg:{}",code, msg);
        return new WebResponse(code, msg, null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WebResponse ExceptionHandler(Exception e) throws Exception {
        LOGGER.error("系统内部错误, 错误信息:", e);
        return new WebResponse(500, "程序员小哥哥出差", null);
    }
}
