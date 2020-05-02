package com.my.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FilterServlet implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
//        System.out.println("filter初始化了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
//        统一编码，过滤防止XSS攻击。
//        System.out.println("filter执行了1");
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        filterChain.doFilter(new XSSWrapper((HttpServletRequest)servletRequest),servletResponse);
//        System.out.println("filter执行了2");
    }

    @Override
    public void destroy()
    {
//        System.out.println("filter销毁了");
    }
}
