package com.heyongqiang.note.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/*
 * 拦截所有
 *      1.放行登录注册界面
 *      2.放行静态资源
 *      3.操作
 *      4.登录状态
 */
@WebFilter("/*")
public class filterAll_login implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取请求路径
        String uri = request.getRequestURI();
        //放行指定界面
        if (uri.contains("login.jsp")){
            filterChain.doFilter(request,response);
            return;
        }
        if (uri.contains("register.jsp")){
            filterChain.doFilter(request,response);
            return;
        }
        // 2.放行静态资源
        if(uri.contains("/static")){
            filterChain.doFilter(request,response);
            return;
        }
        //3.操作
        if(uri.contains("/user")){
            filterChain.doFilter(request,response);
            return;
        }
        if(request.getSession().getAttribute("user") != null){
            filterChain.doFilter(request,response);
            return;
        }
        //4.登录状态
        String username = (String) request.getSession().getAttribute("username");
        if (username !=null ){
            filterChain.doFilter(request,response);
            return;
        }
        //用户免登录
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie : cookies){
                if("user".equals(cookie.getName())){
                    String [] values = cookie.getValue().split("-");
                    String url = "user?actionName=login&username="+values[0]+"&userpwd="+values[1];
                    request.getRequestDispatcher(url).forward(request,response);
                    return;
                }
            }
        }
        //未登录，拦截返回到登录界面
        response.sendRedirect("login.jsp");
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}