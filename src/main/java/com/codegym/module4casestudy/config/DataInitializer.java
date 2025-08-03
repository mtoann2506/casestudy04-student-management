package com.codegym.module4casestudy.config;

import com.codegym.module4casestudy.model.Class;
import com.codegym.module4casestudy.model.Role;
import com.codegym.module4casestudy.model.User;
import com.codegym.module4casestudy.repository.ClassRepository;
import com.codegym.module4casestudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ApplicationContext applicationContext;

    private boolean alreadySetup = false;

    @EventListener(ContextRefreshedEvent.class)
    public void initData() {
        if (alreadySetup) {
            return;
        }

        // Khởi tạo dữ liệu mẫu nếu chưa có
        if (userRepository.count() == 0) {
            initializeUsers();
        }

        if (classRepository.count() == 0) {
            initializeClasses();
        }

        alreadySetup = true;
    }

    private void initializeUsers() {
        // Lazy load PasswordEncoder để tránh circular dependency
        PasswordEncoder passwordEncoder = applicationContext.getBean(PasswordEncoder.class);

        // Tạo admin user
        User admin = new User();
        admin.setUsername("admin@example.com");
        admin.setPassword(passwordEncoder.encode("123456"));
        admin.setFullName("Administrator");
        admin.setEmail("admin@example.com");
        admin.setPhone("0123456789");
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);

        // Tạo teacher user
        User teacher = new User();
        teacher.setUsername("teacher@example.com");
        teacher.setPassword(passwordEncoder.encode("123456"));
        teacher.setFullName("Teacher Example");
        teacher.setEmail("teacher@example.com");
        teacher.setPhone("0123456788");
        teacher.setRole(Role.TEACHER);
        userRepository.save(teacher);

        // Tạo student user
        User student = new User();
        student.setUsername("student@example.com");
        student.setPassword(passwordEncoder.encode("123456"));
        student.setFullName("Student Example");
        student.setEmail("student@example.com");
        student.setPhone("0123456787");
        student.setRole(Role.STUDENT);
        userRepository.save(student);
    }

    private void initializeClasses() {
        //Nhi bỏ dữ liệu mẫu vì có thể tự tạo luôn trên giao diện

        // Tạo một số class mẫu
        // Class class1 = new Class();
        // class1.setClassName("Java Programming");
        // class1.setDescription("Learn Java programming fundamentals");
        // classRepository.save(class1);

        // Class class2 = new Class();
        // class2.setClassName("Web Development");
        // class2.setDescription("Learn web development with Spring");
        // classRepository.save(class2);
    }
}
