package com.spring.study.jdbc.service;

import com.spring.study.jdbc.dao.AccountDao;
import com.spring.study.jdbc.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService_2 {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountService_2 accountService_2;

    public Account getAccount(String username){
        return accountDao.getAccount(username);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void reduceAndAddMoney(){
        accountService_2.deal();
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deal(){
        //扣钱
        accountDao.reduceMoney();
        //制造异常
//        Object o = null;
//        o.toString();
        //加钱
        accountDao.addMoney();
    }

}
