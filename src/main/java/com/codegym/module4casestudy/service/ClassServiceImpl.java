package com.codegym.module4casestudy.service;

import com.codegym.module4casestudy.dto.ClassDto;
import com.codegym.module4casestudy.model.Class;
import com.codegym.module4casestudy.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements IClassService {
    
    @Autowired
    private ClassRepository classRepository;
    
    @Override
    public List<Class> findAll() {
        return classRepository.findAll();
    }
    
    @Override
    public List<Class> findAllActive() {
        return classRepository.findByActive(true);
    }
    
    @Override
    public Optional<Class> findById(Long id) {
        return classRepository.findById(id);
    }
    
    @Override
    public Optional<Class> findByClassName(String className) {
        return classRepository.findByClassName(className);
    }
    
    @Override
    public List<Class> searchByKeyword(String keyword) {
        return classRepository.searchByKeyword(keyword);
    }
    
    @Override
    public Class save(ClassDto classDto) {
        Class class_ = new Class();
        class_.setClassName(classDto.getClassName());
        class_.setDescription(classDto.getDescription());
        class_.setMaxStudents(classDto.getMaxStudents());
        class_.setActive(classDto.isActive());
        return classRepository.save(class_);
    }
    
    @Override
    public Class update(Long id, ClassDto classDto) {
        Optional<Class> existingClass = classRepository.findById(id);
        if (existingClass.isPresent()) {
            Class class_ = existingClass.get();
            class_.setClassName(classDto.getClassName());
            class_.setDescription(classDto.getDescription());
            class_.setMaxStudents(classDto.getMaxStudents());
            class_.setActive(classDto.isActive());
            return classRepository.save(class_);
        }
        throw new RuntimeException("Class not found with id: " + id);
    }
    
    @Override
    public void deleteById(Long id) {
        classRepository.deleteById(id);
    }
    
    @Override
    public boolean existsByClassName(String className) {
        return classRepository.existsByClassName(className);
    }
    
    @Override
    public boolean existsByClassNameAndIdNot(String className, Long id) {
        return classRepository.existsByClassNameAndIdNot(className, id);
    }
} 