package com.codegym.module4casestudy.service;

import com.codegym.module4casestudy.model.StudentClass;

import java.util.List;
import java.util.Optional;

// StudentClassService.java
public interface StudentClassService {
    List<StudentClass> findAll();
    Optional<StudentClass> findById(Long id);
    StudentClass save(StudentClass studentClass);
    void delete(Long id);
}
