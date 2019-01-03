package com.sanbangzi.xcx_web.filter;

import com.sanbangzi.xcx_web.wapper.RequestWrapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Order(1)
@Configuration
@WebFilter(filterName = "streamFilter", urlPatterns = "/*")
public class StreamFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        filterChain.doFilter(new RequestWrapper(httpServletRequest), servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}



    @Override
    public void destroy() {}
}
