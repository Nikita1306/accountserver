package com.example.accountserver.controller;

import com.example.accountserver.model.Account;
import com.example.accountserver.service.AccountServiceImpl;
import com.example.accountserver.service.StatsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
public class AccountController {

    private final AccountServiceImpl accountService;

    private final StatsService statsService;

    public AccountController(AccountServiceImpl accountService, StatsService statsService) {
        this.accountService = accountService;
        this.statsService = statsService;
    }

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Long create(@RequestParam Integer id) {
        log.info("Requested id: " + id);
        statsService.storeRequest("Get");
        return accountService.getAmount(id);
    }

    //TODO add check if account id is present in DB
    @PutMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Account> addToBalance(@RequestBody Account account) {

        statsService.storeRequest("Put");
        accountService.addAmount(account.getId(), account.getBalance());
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping(path = "/stats")
    public double getRequests() {
        return statsService.numberOfRequests();
    }
}
