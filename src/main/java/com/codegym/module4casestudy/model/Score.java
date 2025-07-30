package com.codegym.module4casestudy.model;

import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    private Float midterm;
    private Float finalExam;
    private Float other;
    private Float total;

    public Score() {
    }

    public Score(Long id, Student student, ClassEntity classEntity, Float midterm, Float finalExam, Float other, Float total) {
        this.id = id;
        this.student = student;
        this.classEntity = classEntity;
        this.midterm = midterm;
        this.finalExam = finalExam;
        this.other = other;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public Float getMidterm() {
        return midterm;
    }

    public void setMidterm(Float midterm) {
        this.midterm = midterm;
    }

    public Float getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(Float finalExam) {
        this.finalExam = finalExam;
    }

    public Float getOther() {
        return other;
    }

    public void setOther(Float other) {
        this.other = other;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
