package com.sanbangzi.xcx_web.config;

import com.sanbangzi.xcx_web.interceptor.LoginInterceptor;
import com.sanbangzi.xcx_web.interceptor.RequestParamsNotEmptyMethodArgumentInterceptor;
import com.sanbangzi.xcx_web.interceptor.SignatureInterceptor;
import com.sanbangzi.xcx_web.resolver.CurrentUserResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private RequestParamsNotEmptyMethodArgumentInterceptor requestParamsNotEmptyMethodArgumentInterceptor;

    @Autowired
    private SignatureInterceptor signatureInterceptor;

    @Autowired
    private CurrentUserResolver currentUserResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(signatureInterceptor).order(2).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor).order(3).addPathPatterns("/**");
        registry.addInterceptor(requestParamsNotEmptyMethodArgumentInterceptor).order(4).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(currentUserResolver);
    }
}
