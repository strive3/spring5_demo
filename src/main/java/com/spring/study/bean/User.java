package com.spring.study.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    private int id;
    private String username;

}
