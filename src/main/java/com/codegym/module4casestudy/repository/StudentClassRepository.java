package com.codegym.module4casestudy.repository;

import com.codegym.module4casestudy.model.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass, Long> {
    List<StudentClass> findByStudentId(Long studentId);
    List<StudentClass> findByClassId(Long classId);
}