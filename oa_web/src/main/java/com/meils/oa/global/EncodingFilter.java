package com.meils.oa.global;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 字符编码过滤处理
 * @desc 如果未设置我们使用utf-8
 */
public class EncodingFilter implements Filter {

    private String encoding = "utf-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化时候获取设置的字符编码
        if(filterConfig.getInitParameter("encoding")!=null){
            encoding = filterConfig.getInitParameter("encoding");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        // 设置请求和响应编码
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        // 继续执行
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
