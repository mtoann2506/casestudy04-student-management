package com.codegym.module4casestudy.service;

import com.codegym.module4casestudy.model.ClassEntity;

import java.util.List;
import java.util.Optional;

// ClassService.java
public interface ClassService {
    List<ClassEntity> findAll();
    Optional<ClassEntity> findById(Long id);
    ClassEntity save(ClassEntity classEntity);
    void delete(Long id);
}
