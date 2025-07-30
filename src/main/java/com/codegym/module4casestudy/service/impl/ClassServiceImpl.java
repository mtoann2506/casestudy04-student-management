package com.codegym.module4casestudy.service.impl;

import com.codegym.module4casestudy.model.ClassEntity;
import com.codegym.module4casestudy.repository.ClassRepository;
import com.codegym.module4casestudy.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// ClassServiceImpl.java
@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Override
    public List<ClassEntity> findAll() {
        return classRepository.findAll();
    }

    @Override
    public Optional<ClassEntity> findById(Long id) {
        return classRepository.findById(id);
    }

    @Override
    public ClassEntity save(ClassEntity classEntity) {
        return classRepository.save(classEntity);
    }

    @Override
    public void delete(Long id) {
        classRepository.deleteById(id);
    }
}
