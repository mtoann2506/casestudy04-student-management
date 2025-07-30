package com.codegym.module4casestudy.service;

import com.codegym.module4casestudy.model.Student;

import java.util.List;
import java.util.Optional;

// StudentService.java
public interface StudentService {
    List<Student> findAll();
    Optional<Student> findById(Long id);
    Student save(Student student);
    void delete(Long id);
}
