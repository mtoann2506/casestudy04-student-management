package com.codegym.module4casestudy.config;

import com.codegym.module4casestudy.model.Class;
import com.codegym.module4casestudy.model.Role;
import com.codegym.module4casestudy.model.User;
import com.codegym.module4casestudy.repository.ClassRepository;
import com.codegym.module4casestudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        // Chỉ tạo dữ liệu nếu chưa có user nào
        if (userRepository.count() == 0) {
            createInitialUsers();
        }

        // Chỉ tạo dữ liệu nếu chưa có class nào
        if (classRepository.count() == 0) {
            createInitialClasses();
        }
    }

    private void createInitialUsers() {
        // Tạo admin
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.setFullName("Administrator");
        admin.setEmail("admin@codegym.vn");
        admin.setPhone("0123456789");
        admin.setRole(Role.ADMIN);
        admin.setEnabled(true);
        userRepository.save(admin);

        // Tạo teacher
        User teacher = new User();
        teacher.setUsername("teacher");
        teacher.setPassword(passwordEncoder.encode("123456"));
        teacher.setFullName("Nguyễn Văn Giáo Viên");
        teacher.setEmail("teacher@codegym.vn");
        teacher.setPhone("0123456788");
        teacher.setRole(Role.TEACHER);
        teacher.setEnabled(true);
        userRepository.save(teacher);

        // Tạo student
        User student = new User();
        student.setUsername("student");
        student.setPassword(passwordEncoder.encode("123456"));
        student.setFullName("Lê Văn Sinh Viên");
        student.setEmail("student@codegym.vn");
        student.setPhone("0123456787");
        student.setRole(Role.STUDENT);
        student.setEnabled(true);
        userRepository.save(student);

        System.out.println("Đã tạo dữ liệu user ban đầu!");
    }

    private void createInitialClasses() {
        // Tạo các lớp học mẫu
        Class class1 = new Class("C0323G1", "Lớp Java Web Full Stack", 30);
        classRepository.save(class1);

        Class class2 = new Class("C0323G2", "Lớp Java Backend", 25);
        classRepository.save(class2);

        Class class3 = new Class("C0323G3", "Lớp Frontend React", 20);
        classRepository.save(class3);

        System.out.println("Đã tạo dữ liệu lớp học ban đầu!");
    }
} 