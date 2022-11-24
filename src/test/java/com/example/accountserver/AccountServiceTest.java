package com.example.accountserver;

import com.example.accountserver.service.AccountServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
public class AccountServiceTest {

    @Autowired
    private AccountServiceImpl service;

    @Test
    public void get() {
        Long Account1 = service.getAmount(56);
        Long Account2 = service.getAmount(2);

        getAndPrint(service.getAmount(56));
        getAndPrint(service.getAmount(2));
        getAndPrint(service.getAmount(56));
        getAndPrint(service.getAmount(2));
    }

    private void getAndPrint(Long id) {
        log.info("Account found: {}", service.getAmount(Math.toIntExact(id)));
    }
}