package com.spring.study.jdbc.entity;

import lombok.Data;

@Data
public class Account {
    private Long id;
    private String username;
    private int money;
}
