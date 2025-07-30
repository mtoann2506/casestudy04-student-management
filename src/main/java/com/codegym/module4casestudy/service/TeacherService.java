package com.codegym.module4casestudy.service;

import com.codegym.module4casestudy.model.Teacher;

import java.util.List;
import java.util.Optional;

// TeacherService.java
public interface TeacherService {
    List<Teacher> findAll();
    Optional<Teacher> findById(Long id);
    Teacher save(Teacher teacher);
    void delete(Long id);
}
