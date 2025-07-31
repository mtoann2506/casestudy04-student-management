package com.codegym.module4casestudy.service;

import com.codegym.module4casestudy.dto.ClassDto;
import com.codegym.module4casestudy.model.Class;

import java.util.List;
import java.util.Optional;

public interface IClassService {
    
    List<Class> findAll();
    
    List<Class> findAllActive();
    
    Optional<Class> findById(Long id);
    
    Optional<Class> findByClassName(String className);
    
    List<Class> searchByKeyword(String keyword);
    
    Class save(ClassDto classDto);
    
    Class update(Long id, ClassDto classDto);
    
    void deleteById(Long id);
    
    boolean existsByClassName(String className);
    
    boolean existsByClassNameAndIdNot(String className, Long id);
} 