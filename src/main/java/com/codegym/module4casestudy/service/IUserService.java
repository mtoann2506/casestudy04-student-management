package com.codegym.module4casestudy.service;

import com.codegym.module4casestudy.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService {
    Optional<User> findByUsername(String username);
}
