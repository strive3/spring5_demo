package com.spring.study.jdbc.proxy;

import com.spring.study.jdbc.service.AccountService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author duxiaopeng
 * @Date 2021/3/18 3:33 下午
 * @Description TODO
 */
public class AccountProxy implements InvocationHandler {

    private Object object;

    public AccountProxy(Object o){
        this.object = o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法执行前");
        Object invoke = method.invoke(object, args);
        System.out.println("方法执行后");
        return invoke;
    }
}
