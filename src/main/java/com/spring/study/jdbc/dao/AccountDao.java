package com.spring.study.jdbc.dao;

import com.spring.study.jdbc.entity.Account;

public interface AccountDao {
    void addMoney();

    void reduceMoney();

    Account getAccount(String username);
}
