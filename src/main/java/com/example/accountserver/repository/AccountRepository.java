package com.example.accountserver.repository;

import com.example.accountserver.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
