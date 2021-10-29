package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {//可以代替过滤器和拦截器的作用
//    private final static Logger log = LoggerFactory.getLogger(LogAspect.class);
//
//    //定义一个切点
//    @Pointcut("execution(public * com.example.demo.controller.*.*(..))")
//    public void controllerPointcut(){}
//
//    @Before("controllerPointcut()")
//    public void doBefore(JoinPoint joinPoint){
//        //请求参数
//        Object[] objects = joinPoint.getArgs();
//        System.out.println("---------------start aop------------");
//        log.info(objects.toString());
//    }

}
