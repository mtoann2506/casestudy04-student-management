package com.codegym.module4casestudy.service;

import com.codegym.module4casestudy.model.Schedule;

import java.util.List;
import java.util.Optional;

// ScheduleService.java
public interface ScheduleService {
    List<Schedule> findAll();
    Optional<Schedule> findById(Long id);
    Schedule save(Schedule schedule);
    void delete(Long id);
}
