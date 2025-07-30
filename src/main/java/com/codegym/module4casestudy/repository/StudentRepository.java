package com.codegym.module4casestudy.repository;

import com.codegym.module4casestudy.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByAccountId(Long accountId);
}