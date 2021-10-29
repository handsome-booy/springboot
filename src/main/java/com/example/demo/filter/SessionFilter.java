package com.example.demo.filter;

import com.example.demo.common.Const;
import com.example.demo.pojo.User;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.JsonUtil;
import com.example.demo.util.RedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "sessionFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {

    static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        System.out.println(request.getLocalPort());//输出端口号

        String loginToken = CookieUtil.redaLoginToken(request);
        if(loginToken != null){
            String userJson = RedisPoolUtil.get(loginToken);
            User user = JsonUtil.string2Obj(userJson, User.class);
            if(user != null){
                RedisPoolUtil.expire(loginToken,180L);//每次登陆后，就会把cookie的有效期变为30分钟
            }
        }
        filterChain.doFilter(request, response);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
