package com.spring.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * 事务配置类
 */
public class TransactionConfig {

    @Bean
    public PlatformTransactionManager getTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
