package com.test.banking.controller;

import com.test.banking.entity.Account;
import com.test.banking.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "banking/")
public class AccountController {

    @Autowired
    AccountServices accountServices;

    @PostMapping("createAccount")
    private ResponseEntity<Account> createAccount(@RequestBody Account account){
        return new ResponseEntity<>(accountServices.createAccount(account), HttpStatus.CREATED);
    }

    @PostMapping("depositAmount")
    private ResponseEntity<String> depositAmount(@RequestBody Account account){
        return new ResponseEntity<>(accountServices.depositAmount(account), HttpStatus.OK);
    }

    @PostMapping("withdrawAmount")
    private ResponseEntity<String> withdrawAmount(@RequestBody Account account){
        return new ResponseEntity<>(accountServices.withdrawAccount(account), HttpStatus.OK);
    }

    @GetMapping("getAccountDetails/{accountId}")
    private ResponseEntity<Account> getAccountDetails(@PathVariable("accountId")long accountId){
        return new ResponseEntity<>(accountServices.getAccountDetails(accountId), HttpStatus.OK);
    }
}
