package com.spring.study.config;

import com.spring.study.bean.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author duxiaopeng
 * @Date 2021/3/7 9:52 下午
 * @Description TODO
 */
public class MyBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        BeanDefinition beanDefinition = new RootBeanDefinition(User.class);
        registry.registerBeanDefinition("useraaaa", beanDefinition);
    }
}
