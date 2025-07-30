package com.codegym.module4casestudy.model;

import javax.persistence.*;

@Entity
@Table(name = "class")
public class ClassEntity {  // Tránh trùng từ khóa 'class'
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private String term;

    public ClassEntity() {
    }

    public ClassEntity(Long id, String name, Subject subject, Teacher teacher, String term) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.teacher = teacher;
        this.term = term;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
