package com.spring.study.jdbc.dao.impl;

import com.spring.study.jdbc.dao.AccountDao;
import com.spring.study.jdbc.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addMoney() {
        String sql = "update t_account set money = money+? where username = ?";
        int mary = jdbcTemplate.update(sql, 100, "mary");
        System.out.println(mary);
    }

    @Override
    public void reduceMoney() {
        String sql = "update t_account set money = money-? where username = ?";
        int lucy = jdbcTemplate.update(sql, 100, "lucy");
        System.out.println(lucy);
    }

    @Override
    public Account getAccount(String username) {
        String sql = "select * from t_account where username = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class), username);
    }
}
