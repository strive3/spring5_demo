package com.spring.study.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @Author duxiaopeng
 * @Date 2021/3/9 9:59 上午
 * @Description TODO
 */
@Component(value = "myFactoryBean")
public class MyFactoryBean implements FactoryBean<Temp> {

    @Override
    public Temp getObject() throws Exception {
        return new Temp();
    }

    @Override
    public Class<?> getObjectType() {
        return Temp.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
