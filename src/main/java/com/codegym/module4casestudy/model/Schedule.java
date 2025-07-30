package com.codegym.module4casestudy.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private Shift shift;

    private LocalTime startTime;
    private LocalTime endTime;
    private String location;


    public Schedule() {
    }

    public Schedule(Long id, ClassEntity classEntity, LocalDate date, Shift shift, LocalTime startTime, LocalTime endTime, String location) {
        this.id = id;
        this.classEntity = classEntity;
        this.date = date;
        this.shift = shift;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public enum Shift {
        SANG, CHIEU, TOI
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
