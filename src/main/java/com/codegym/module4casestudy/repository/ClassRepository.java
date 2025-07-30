package com.codegym.module4casestudy.repository;

import com.codegym.module4casestudy.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    List<ClassEntity> findByTeacherId(Long teacherId);
    List<ClassEntity> findBySubjectId(Long subjectId);
}