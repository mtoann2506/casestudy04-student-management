package com.codegym.module4casestudy.controller;

import com.codegym.module4casestudy.model.User;
import com.codegym.module4casestudy.model.Class;
import com.codegym.module4casestudy.model.Subject;
import com.codegym.module4casestudy.service.IUserService;
import com.codegym.module4casestudy.service.IClassService;
import com.codegym.module4casestudy.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IClassService classService;

    @Autowired
    private ISubjectService subjectService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Helper method để lấy thông tin sinh viên hiện tại
    private User getCurrentStudent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userService.findByUsername(username).orElse(null);
    }

    @GetMapping("/dashboard")
    public String showStudentDashboard(Model model) {
        User student = getCurrentStudent();
        if (student == null) {
            model.addAttribute("error", "Không tìm thấy thông tin sinh viên!");
            return "redirect:/login";
        }

        // Thống kê cơ bản cho dashboard
        List<Class> studentClasses = classService.findAll(); // Tạm thời lấy tất cả, sẽ filter sau
        model.addAttribute("student", student);
        model.addAttribute("totalClasses", studentClasses.size());
        model.addAttribute("recentClasses", studentClasses.size() > 3 ? studentClasses.subList(0, 3) : studentClasses);

        return "student/student-panel";
    }

    @GetMapping("/profile")
    public String showStudentProfile(Model model) {
        User student = getCurrentStudent();
        if (student == null) {
            model.addAttribute("error", "Không tìm thấy thông tin sinh viên!");
            return "redirect:/login";
        }

        model.addAttribute("student", student);
        return "student/student-profile";
    }

    @PostMapping("/update-profile")
    public String updateStudentProfile(
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String fullName,
            @RequestParam(required = false) String passwordOld,
            @RequestParam(required = false) String password,
            RedirectAttributes redirectAttributes) {

        try {
            User student = getCurrentStudent();
            if (student == null) {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy thông tin sinh viên!");
                return "redirect:/student/profile";
            }

            // Cập nhật thông tin cơ bản
            student.setEmail(email);
            student.setPhone(phone);
            student.setFullName(fullName);

            // Xử lý đổi mật khẩu
            if (password != null && !password.trim().isEmpty()) {
                if (passwordOld == null || passwordOld.trim().isEmpty()) {
                    redirectAttributes.addFlashAttribute("error", "Vui lòng nhập mật khẩu cũ!");
                    return "redirect:/student/profile";
                }

                // Kiểm tra mật khẩu cũ
                if (!passwordEncoder.matches(passwordOld, student.getPassword())) {
                    redirectAttributes.addFlashAttribute("error", "Mật khẩu cũ không đúng!");
                    return "redirect:/student/profile";
                }

                // Mã hóa và cập nhật mật khẩu mới
                student.setPassword(passwordEncoder.encode(password));
            }

            // Lưu thông tin
            userService.save(student);
            redirectAttributes.addFlashAttribute("message", "Cập nhật thông tin thành công!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật thông tin!");
        }

        return "redirect:/student/profile";
    }

    @GetMapping("/classes")
    public String showStudentClasses(Model model) {
        User student = getCurrentStudent();
        if (student == null) {
            return "redirect:/login";
        }

        // Tạm thời lấy tất cả lớp, sau sẽ filter theo student
        List<Class> studentClasses = classService.findAll();
        List<Class> availableClasses = classService.findAll(); // Lớp có thể đăng ký

        model.addAttribute("student", student);
        model.addAttribute("studentClasses", studentClasses);
        model.addAttribute("availableClasses", availableClasses);

        return "student/student-classes";
    }

    @GetMapping("/classes/{classId}")
    public String showClassDetails(@PathVariable Long classId, Model model) {
        User student = getCurrentStudent();
        if (student == null) {
            return "redirect:/login";
        }

        Class studentClass = classService.findById(classId).orElse(null);
        if (studentClass == null) {
            model.addAttribute("error", "Không tìm thấy lớp học!");
            return "redirect:/student/classes";
        }

        model.addAttribute("student", student);
        model.addAttribute("class", studentClass);

        return "student/student-class-details";
    }

    @GetMapping("/grades")
    public String showStudentGrades(Model model) {
        User student = getCurrentStudent();
        if (student == null) {
            return "redirect:/login";
        }

        List<Class> studentClasses = classService.findAll();
        List<Subject> subjects = subjectService.findActiveSubjects();

        model.addAttribute("student", student);
        model.addAttribute("classes", studentClasses);
        model.addAttribute("subjects", subjects);

        return "student/student-grades";
    }

    @GetMapping("/schedule")
    public String showStudentSchedule(Model model) {
        User student = getCurrentStudent();
        if (student == null) {
            return "redirect:/login";
        }

        List<Class> studentClasses = classService.findAll();
        model.addAttribute("student", student);
        model.addAttribute("classes", studentClasses);

        return "student/student-schedule";
    }

    @PostMapping("/classes/{classId}/register")
    public String registerClass(
            @PathVariable Long classId,
            RedirectAttributes redirectAttributes) {

        User student = getCurrentStudent();
        if (student == null) {
            return "redirect:/login";
        }

        try {
            Class classToRegister = classService.findById(classId).orElse(null);
            if (classToRegister == null) {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy lớp học!");
                return "redirect:/student/classes";
            }

            // TODO: Implement class registration logic
            redirectAttributes.addFlashAttribute("message", "Đăng ký lớp học thành công!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi đăng ký lớp!");
        }

        return "redirect:/student/classes";
    }

    @PostMapping("/classes/{classId}/unregister")
    public String unregisterClass(
            @PathVariable Long classId,
            RedirectAttributes redirectAttributes) {

        User student = getCurrentStudent();
        if (student == null) {
            return "redirect:/login";
        }

        try {
            // TODO: Implement class unregistration logic
            redirectAttributes.addFlashAttribute("message", "Hủy đăng ký lớp học thành công!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi hủy đăng ký lớp!");
        }

        return "redirect:/student/classes";
    }
}
