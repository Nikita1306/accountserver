package com.example.accountserver.service;

import com.example.accountserver.exception.AccountNotFoundException;
import com.example.accountserver.model.Account;
import com.example.accountserver.repository.AccountRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Log4j2
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    //TODO add check if not present
    @Override
    @CachePut(value="accounts", key="#id")
    public Long getAmount(Integer id) {
        if (accountRepository.findById(id).isPresent())
            return accountRepository.findById(id).get().getBalance();
        else return  null;
    }

    @Override
    public void addAmount(Integer id, Long value) {
        Optional<Account> account = accountRepository.findById(id);
        log.warn("Value: " + value);
        if (account.isPresent()) {
            Account account1 = account.get();
            log.warn("Current balance: " + account1.getBalance());
            if (account1.getBalance() == null)
                account1.setBalance(value);
            else account1.setBalance(account1.getBalance() + value);
            accountRepository.save(account1);
            log.warn("After balance: " + account1.getBalance());
        } else {
            throw new AccountNotFoundException(id);
        }
    }
}
