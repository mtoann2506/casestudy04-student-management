package com.codegym.module4casestudy.repository;

import com.codegym.module4casestudy.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByClassId(Long classId);
    List<Schedule> findByDate(java.sql.Date date);
    List<Schedule> findByShift(String shift);
}