package com.sanbangzi.xcx_web.interceptor;

import com.sanbangzi.common.utils.StringUtil;
import com.sanbangzi.user_service_api.consts.UserCacheKeyConst;
import com.sanbangzi.xcx_web.annotation.IgnoreLogin;
import com.sanbangzi.xcx_web.consts.XcxConst;
import com.sanbangzi.xcx_web.exception.XcxErrorCode;
import com.sanbangzi.xcx_web.exception.XcxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    @Value("${spring.profiles.active}")
    private String profiles;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isRelease(handler)) {
            return true;
        }
        String remoteAccessToken = request.getHeader(XcxConst.ACCESS_TOKEN_KEY);
        if (StringUtil.isBlank(remoteAccessToken)) {
            throw XcxException.throwErrorCode(XcxErrorCode.ACCESS_TOKEN_ERROR);
        }
        String cacheKey = UserCacheKeyConst.XCX_ACCESS_TOKEN + remoteAccessToken;
        String localAccessToken = stringRedisTemplate.opsForValue().get(cacheKey);
        if (StringUtil.isBlank(localAccessToken)) {
            throw XcxException.throwErrorCode(XcxErrorCode.ACCESS_TOKEN_ERROR);
        }
        return true;
    }

    /**
     * 是否放行
     * @param handler
     * @return
     */
    private boolean isRelease(Object handler) {
        if (Arrays.asList("dev", "test").contains(profiles)) {
            return true;
        }
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        IgnoreLogin annotation = AnnotationUtils.findAnnotation(((HandlerMethod) handler).getMethod(), IgnoreLogin.class);
        return null != annotation;
    }
}
