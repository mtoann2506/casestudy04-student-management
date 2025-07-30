package com.codegym.module4casestudy.service.impl;

import com.codegym.module4casestudy.model.Schedule;
import com.codegym.module4casestudy.repository.ScheduleRepository;
import com.codegym.module4casestudy.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// ScheduleServiceImpl.java
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}
