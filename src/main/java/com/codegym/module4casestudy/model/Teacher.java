package com.codegym.module4casestudy.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id", unique = true)
    private Account account;

    private String fullName;
    private String degree;
    private LocalDate birthday;
    private String phone;
    private String email;

    public Teacher() {
    }

    public Teacher(Long id, Account account, String fullName, String degree, LocalDate birthday, String phone, String email) {
        this.id = id;
        this.account = account;
        this.fullName = fullName;
        this.degree = degree;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
