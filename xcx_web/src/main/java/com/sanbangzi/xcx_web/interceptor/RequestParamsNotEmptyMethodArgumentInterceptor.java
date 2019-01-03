package com.sanbangzi.xcx_web.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.sanbangzi.common.utils.StringUtil;
import com.sanbangzi.xcx_web.annotation.RequestParamsNotEmpty;
import com.sanbangzi.xcx_web.exception.XcxErrorCode;
import com.sanbangzi.xcx_web.exception.XcxException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

@Component
public class RequestParamsNotEmptyMethodArgumentInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        RequestParamsNotEmpty annotation = AnnotationUtils.findAnnotation(method, RequestParamsNotEmpty.class);
        if (null == annotation) {
            return true;
        }
        String[] params = annotation.names();
        if (ArrayUtils.isEmpty(params)) {
            return true;
        }

        String requestMethod = request.getMethod();
        String contentType = request.getContentType();

        if ("GET".equalsIgnoreCase(requestMethod)) {
            checkGet(request, params);
        } else if ("POST".equalsIgnoreCase(requestMethod) && StringUtil.isNotBlank(contentType) && "application/json".equalsIgnoreCase(contentType)) {
            checkPost(request, params);
        } else {
            throw XcxException.throwErrorCode(XcxErrorCode.DATA_CHECK_ERROR);
        }
        return true;
    }

    /**
     * 校验get请求
     * @param request
     * @param params
     * @throws Exception
     */
    private void checkGet(HttpServletRequest request, String[] params) throws Exception {
        String value = null;
        for (String paramName : params) {
            value = request.getParameter(paramName);
            if (StringUtil.isAnyEmpty(paramName, value)) {
                throw XcxException.throwParamNull(paramName);
            }
        }
    }

    /**
     * 校验post请求json体
     * @param request
     * @param checkParams
     * @throws Exception
     */
    private void checkPost(HttpServletRequest request,  String[] checkParams) throws Exception {
        String requestParam = IOUtils.toString(request.getInputStream(), "UTF-8");
        Map<String, String> requestParamMap = JSON.parseObject(requestParam, Map.class);
        String value = null;
        for (String paramName : checkParams) {
            value = String.valueOf(requestParamMap.get(paramName));
            if (StringUtil.isAnyEmpty(paramName, value)) {
                throw XcxException.throwParamNull(paramName);
            }
        }
    }
}
