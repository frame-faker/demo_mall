package com.sanbangzi.user_service_api.api;

import com.sanbangzi.domain.dto.res.XcxLoginResDTO;
import com.sanbangzi.user_service_api.exception.UserException;

public interface XcxLoginService {

    XcxLoginResDTO login(String code) throws UserException;

    XcxLoginResDTO register(String code, String userInfo) throws Exception;
}
