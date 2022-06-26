package com.test.banking.services.impl;

import ch.qos.logback.classic.spi.IThrowableProxy;
import com.test.banking.entity.Account;
import com.test.banking.respository.AccountRepository;
import com.test.banking.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountServices {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {
        account.setCreatedAt(LocalDateTime.now());
        return accountRepository.save(account);
    }

    @Override
    public String depositAmount(Account accountDetail) {
        if (accountDetail.getAccountId() == 0) {
            throw new RuntimeException("accountId not found");
        }
        Optional<Account> account = accountRepository.findById(accountDetail.getAccountId());
        if(account.isPresent()){
            account.get().setAmount(account.get().getAmount()+accountDetail.getAmount());
            Account savedAmount = accountRepository.save(account.get());
            account.get().setUpdatedAt(LocalDateTime.now());
            return String.format("Amount : %s has been deposited successfully, Balance : %s",accountDetail.getAmount(),
                    savedAmount.getAmount());
        }
        return "Amount deposit failed...!!!";
    }

    @Override
    public String withdrawAccount(Account accountDetail) {
        if (accountDetail.getAccountId() == 0) {
            throw new RuntimeException("accountId not found");
        }
        Optional<Account> account = accountRepository.findById(accountDetail.getAccountId());
        if(account.isPresent()){
            account.get().setAmount(account.get().getAmount()-accountDetail.getAmount());
            account.get().setUpdatedAt(LocalDateTime.now());
            Account savedAmount = accountRepository.save(account.get());
            return String.format("Amount : %s has been withdrawn successfully, Balance : %s",accountDetail.getAmount(),
                    savedAmount.getAmount());
        }
        return "Amount withdraw failed...!!!";
    }

    @Override
    public Account getAccountDetails(long accountId) {
        if (accountId == 0) {
            throw new RuntimeException("accountId not found");
        }
        Optional<Account> account = accountRepository.findById(accountId);
        if(account.isPresent()){
            return account.get();
        }
        throw new RuntimeException("account not found");
    }


}
