package com.codegym.module4casestudy.service.impl;

import com.codegym.module4casestudy.model.Score;
import com.codegym.module4casestudy.repository.ScoreRepository;
import com.codegym.module4casestudy.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// ScoreServiceImpl.java
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public List<Score> findAll() {
        return scoreRepository.findAll();
    }

    @Override
    public Optional<Score> findById(Long id) {
        return scoreRepository.findById(id);
    }

    @Override
    public Score save(Score score) {
        return scoreRepository.save(score);
    }

    @Override
    public void delete(Long id) {
        scoreRepository.deleteById(id);
    }
}
