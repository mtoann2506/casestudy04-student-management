package com.codegym.module4casestudy.service;

import com.codegym.module4casestudy.model.Score;

import java.util.List;
import java.util.Optional;

// ScoreService.java
public interface ScoreService {
    List<Score> findAll();
    Optional<Score> findById(Long id);
    Score save(Score score);
    void delete(Long id);
}
