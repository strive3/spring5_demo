package com.spring.study.jdbc.proxy;

import com.spring.study.jdbc.dao.AccountDao;
import com.spring.study.jdbc.dao.impl.AccountDaoImpl;
import com.spring.study.jdbc.entity.Account;

import java.lang.reflect.Proxy;

/**
 * @Author duxiaopeng
 * @Date 2021/3/18 3:36 下午
 * @Description TODO
 */
public class JDKProxy {
    public static Object getProxy(Object target){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new AccountProxy(target));
    }

    public static void main(String[] args) {
        AccountDao accountDaoImpl = (AccountDao)getProxy(new AccountDaoImpl());
        Account lucy = accountDaoImpl.getAccount("lucy");
        System.out.println(lucy);
    }
}
