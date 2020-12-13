package com.spring.study.jdbc.service;

import com.spring.study.jdbc.dao.AccountDao;
import com.spring.study.jdbc.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    public Account getAccount(String username){
        return accountDao.getAccount(username);
    }

    public void reduceAndAddMoney(){
        //扣钱
        accountDao.reduceMoney();
        //制造异常
        Object o = null;
        o.toString();
        //加钱
        accountDao.addMoney();
    }
}
