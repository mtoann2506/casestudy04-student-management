package com.codegym.module4casestudy.service.impl;

import com.codegym.module4casestudy.model.StudentClass;
import com.codegym.module4casestudy.repository.StudentClassRepository;
import com.codegym.module4casestudy.service.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// StudentClassServiceImpl.java
@Service
public class StudentClassServiceImpl implements StudentClassService {

    @Autowired
    private StudentClassRepository studentClassRepository;

    @Override
    public List<StudentClass> findAll() {
        return studentClassRepository.findAll();
    }

    @Override
    public Optional<StudentClass> findById(Long id) {
        return studentClassRepository.findById(id);
    }

    @Override
    public StudentClass save(StudentClass studentClass) {
        return studentClassRepository.save(studentClass);
    }

    @Override
    public void delete(Long id) {
        studentClassRepository.deleteById(id);
    }
}
