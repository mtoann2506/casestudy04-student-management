package com.codegym.module4casestudy.service;

import com.codegym.module4casestudy.model.Subject;

import java.util.List;
import java.util.Optional;

// SubjectService.java
public interface SubjectService {
    List<Subject> findAll();
    Optional<Subject> findById(Long id);
    Subject save(Subject subject);
    void delete(Long id);
}
