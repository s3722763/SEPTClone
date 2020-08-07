package com.rmit.majorproject.BackEnd.services;

import com.rmit.majorproject.BackEnd.Repositories.AccountRepository;
import com.rmit.majorproject.BackEnd.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account saveOrUpdateAccount(Account account){
        //business logic
        return (Account) accountRepository.save(account);
    }
}
