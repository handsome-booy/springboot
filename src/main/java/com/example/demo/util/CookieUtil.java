package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CookieUtil {
    private final static String COOKIE_DOMAIN = "xxj.com";
    private final static String COOKIE_NAME = "login_token";

    public static void writeLoginToken(HttpServletResponse response,String token){
        Cookie ck = new Cookie(COOKIE_NAME,token);
        ck.setPath("/");//代表设置在根目录
        ck.setDomain(COOKIE_DOMAIN);
        ck.setMaxAge(60*60*24*365);//-1代表永久
        log.info("cookieName{},cookieValue{}",ck.getName(),ck.getValue());
        response.addCookie(ck);
    }

    public static String redaLoginToken(HttpServletRequest request){
        Cookie[] cks = request.getCookies();
        if(cks != null){
            for(Cookie ck :cks){
                log.info("cookieName{},cookieValue{}",ck.getName(),ck.getValue());
                if(COOKIE_NAME.equals(ck.getName())){
                    log.info("cookieName{},cookieValue{}",ck.getName(),ck.getValue());
                    return ck.getValue();
                }
            }
        }
        return null;
    }

    public static void delLoginToken(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cks = request.getCookies();
        if(cks != null){
            for(Cookie ck :cks){
                log.info("cookieName{},cookieValue{}",ck.getName(),ck.getValue());
                if(COOKIE_DOMAIN.equals(ck.getName())){
                    ck.setDomain(COOKIE_DOMAIN);
                    ck.setPath("/");
                    ck.setMaxAge(0);//代表删除了cookie
                    log.info("cookieName{},cookieValue{}",ck.getName(),ck.getValue());
                    response.addCookie(ck);
                    return;
                }
            }
        }
    }
}
