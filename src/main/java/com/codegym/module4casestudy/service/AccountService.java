package com.codegym.module4casestudy.service;

import com.codegym.module4casestudy.model.Account;

import java.util.List;
import java.util.Optional;

// AccountService.java
public interface AccountService {
    List<Account> findAll();
    Optional<Account> findById(Long id);
    Optional<Account> findByUsername(String username);
    Account save(Account account);
    void delete(Long id);
}
