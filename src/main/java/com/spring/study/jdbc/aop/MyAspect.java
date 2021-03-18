package com.spring.study.jdbc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author duxiaopeng
 * @Date 2021/3/18 11:14 上午
 * @Description TODO
 */
@Aspect
@Component
public class MyAspect {
    @Pointcut(value = "execution(* com.spring.study.jdbc.service.*.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object o = joinPoint.proceed();
        System.out.println(o);
        long end = System.currentTimeMillis();
        System.out.println("耗时: " + (end - start));
    }
}
