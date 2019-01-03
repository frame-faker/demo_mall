package com.sanbangzi.xcx_web.resolver;

import com.sanbangzi.common.utils.StringUtil;
import com.sanbangzi.domain.dto.res.UserResDTO;
import com.sanbangzi.user_service_api.api.UserService;
import com.sanbangzi.user_service_api.consts.UserCacheKeyConst;
import com.sanbangzi.xcx_web.annotation.CurrentUser;
import com.sanbangzi.xcx_web.consts.XcxConst;
import com.sanbangzi.xcx_web.exception.XcxErrorCode;
import com.sanbangzi.xcx_web.exception.XcxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentUserResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getParameterType().isAssignableFrom(UserResDTO.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 取出的登录用户token
        String token = webRequest.getHeader(XcxConst.ACCESS_TOKEN_KEY);
        String key = UserCacheKeyConst.XCX_ACCESS_TOKEN + token;
        String userIdStr = redisTemplate.opsForValue().get(key).toString();
        if (StringUtil.isBlank(userIdStr)) {
            throw XcxException.throwErrorCode(XcxErrorCode.ACCESS_TOKEN_ERROR);
        }
        return userService.getById(Long.parseLong(userIdStr));
    }
}
