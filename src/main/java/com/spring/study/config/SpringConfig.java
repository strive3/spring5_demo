package com.spring.study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(value = "com.spring.study")//配置包扫描
@Configuration
@Import({JdbcConfig.class,TransactionConfig.class})//将配置类导入
@EnableTransactionManagement//开始事务注解
public class SpringConfig {
}
