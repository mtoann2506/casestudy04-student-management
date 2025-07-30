package com.codegym.module4casestudy.repository;

import com.codegym.module4casestudy.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByStudentId(Long studentId);
    List<Score> findByClassId(Long classId);
}