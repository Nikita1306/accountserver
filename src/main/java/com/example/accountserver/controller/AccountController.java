package com.example.accountserver.controller;

import com.example.accountserver.model.Account;
import com.example.accountserver.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long create(@RequestParam Integer id) {
        System.out.println("test");
        return accountService.getAmount(id);
    }

    //TODO add check if account id is present in DB
    @PutMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Account> addToBalance(@RequestBody Account account) {
        accountService.addAmount(account.getId(), account.getBalance());
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
