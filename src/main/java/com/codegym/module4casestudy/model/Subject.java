package com.codegym.module4casestudy.model;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer credit;
    private String scoringFormula;

    public Subject() {
    }
    public Subject(Long id, String name, Integer credit, String scoringFormula) {
        this.id = id;
        this.name = name;
        this.credit = credit;
        this.scoringFormula = scoringFormula;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getScoringFormula() {
        return scoringFormula;
    }

    public void setScoringFormula(String scoringFormula) {
        this.scoringFormula = scoringFormula;
    }
}
