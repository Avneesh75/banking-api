package com.test.banking.services;

import com.test.banking.entity.Account;

public interface AccountServices {
    Account createAccount(Account account);
    String depositAmount(Account account);
    String withdrawAccount(Account account);
    Account getAccountDetails(long accountId);
}
