package com.sanbangzi.xcx_web.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.sanbangzi.common.utils.DigestUtil;
import com.sanbangzi.common.utils.StringUtil;
import com.sanbangzi.xcx_web.annotation.IgnoreSignature;
import com.sanbangzi.xcx_web.exception.XcxErrorCode;
import com.sanbangzi.xcx_web.exception.XcxException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

@Component
public class SignatureInterceptor extends HandlerInterceptorAdapter {

    private static final String SIGN_KEY = "signKey";

    private static final String SIGN_SALT = "<your sign salt>";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        IgnoreSignature ignoreSignature = AnnotationUtils.findAnnotation(method, IgnoreSignature.class);
        if (null != ignoreSignature) {
            return true;
        }

        String requestMethod = request.getMethod();
        String contentType = request.getContentType();

        // 获取参数
        String remoteSign = "";
        Map<String, String> signMap = Maps.newTreeMap();
        if ("GET".equalsIgnoreCase(requestMethod)) {
            remoteSign = request.getParameter(SIGN_KEY);
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String key = entry.getKey();
                if (!SIGN_KEY.equals(key)) {
                    signMap.put(key, entry.getValue()[0]);
                }
            }
        } else if ("POST".equalsIgnoreCase(requestMethod) && StringUtil.isNotBlank(contentType) && "application/json".equalsIgnoreCase(contentType)) {
            String paramsStr = IOUtils.toString(request.getInputStream(), "UTF-8");
            signMap = JSON.parseObject(paramsStr, TreeMap.class);
            remoteSign = signMap.get(SIGN_KEY);
            signMap.remove(SIGN_KEY);
        } else {
            throw XcxException.throwErrorCode(XcxErrorCode.DATA_CHECK_ERROR);
        }
        return sign(remoteSign, signMap);
    }

    private static boolean sign(String remoteSign,  Map<String, String> signMap) {
        if (StringUtil.isBlank(remoteSign)) {
            throw XcxException.throwErrorCode(XcxErrorCode.SIGN_ERROR);
        }
        String sign = Joiner.on("&").withKeyValueSeparator("=").join(signMap);
        sign = new StringBuilder(SIGN_SALT).append(sign).append(SIGN_SALT).toString();
        String localSign = DigestUtil.md5Hex(sign);
        boolean equals = StringUtil.equals(remoteSign, localSign);
        if (!equals) {
            throw XcxException.throwErrorCode(XcxErrorCode.SIGN_ERROR);
        }
        return true;
    }


}
