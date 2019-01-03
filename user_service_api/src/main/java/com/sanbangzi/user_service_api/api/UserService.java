package com.sanbangzi.user_service_api.api;

import com.sanbangzi.domain.dto.req.UserReqDTO;
import com.sanbangzi.domain.dto.res.UserResDTO;
import com.sanbangzi.user_service_api.exception.UserException;

import java.util.Optional;

public interface UserService {

    UserResDTO getById(Long userId) throws UserException;

    UserResDTO getByOpenId(String openId) throws UserException;

    Long save(UserReqDTO user) throws Exception;
}
