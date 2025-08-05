# Sửa lỗi Student Profile

## Vấn đề
- Tương tự như teacher, student profile có thể bị trống khi navigate
- Template sử dụng `student.createdAt` nhưng User model không có field này
- Method `showStudentProfile` không return template đúng cách

## Giải pháp đã áp dụng

### 1. Sửa Controller
- **Vấn đề**: Method `showStudentProfile` không return template
- **Giải pháp**: Sửa method để return đúng template và xử lý lỗi tốt hơn

```java
@GetMapping("/profile")
public String showStudentProfile(Model model) {
    User student = getCurrentStudent();
    if (student == null) {
        model.addAttribute("error", "Không tìm thấy thông tin sinh viên! Vui lòng đăng nhập lại.");
        // Thêm một student mẫu để template không bị lỗi
        student = new User();
        student.setUsername("Unknown");
        student.setFullName("Không xác định");
        student.setEmail("");
        student.setPhone("");
    }

    model.addAttribute("student", student);
    return "student/student-profile";
}
```

### 2. Cải thiện getCurrentStudent()
```java
private User getCurrentStudent() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
        System.out.println("DEBUG: Authentication is null");
        return null;
    }
    
    String username = auth.getName();
    System.out.println("DEBUG: Current username: " + username);
    
    User student = userService.findByUsername(username).orElse(null);
    if (student == null) {
        System.out.println("DEBUG: Student not found for username: " + username);
    } else {
        System.out.println("DEBUG: Student found: " + student.getFullName() + " (ID: " + student.getId() + ")");
    }
    
    return student;
}
```

### 3. Sửa Template
- **Vấn đề**: Template sử dụng `${student.createdAt}` nhưng User model không có field này
- **Giải pháp**: Thay thế bằng thông tin trạng thái và mã sinh viên

```html
<!-- Trước -->
<span th:text="${#temporals.format(student.createdAt, 'dd/MM/yyyy')}">01/09/2024</span>

<!-- Sau -->
<span class="badge bg-success" th:if="${student.enabled}">Hoạt động</span>
<span class="badge bg-danger" th:unless="${student.enabled}">Không hoạt động</span>
```

### 4. Thêm Debug Endpoint
- Thêm endpoint `/student/debug` để kiểm tra thông tin authentication và student data
- Template debug: `student-debug.html`

## Testing

### 1. Test cơ bản
1. Đăng nhập với tài khoản student
2. Truy cập `/student/profile`
3. Kiểm tra xem thông tin có hiển thị đúng không

### 2. Test navigation
1. Đăng nhập với tài khoản student
2. Truy cập `/student/profile`
3. Click vào "Lớp học của tôi" trong sidebar
4. Click vào "Tài khoản cá nhân" trong sidebar
5. Kiểm tra xem profile có hiển thị đúng không

### 3. Test debug
1. Truy cập `/student/debug`
2. Kiểm tra thông tin authentication và student
3. Sử dụng thông tin này để debug nếu có vấn đề

## Files đã sửa

### Controllers
- `StudentController.java` - Cải thiện error handling và thêm debug

### Templates
- `student-profile.html` - Sửa template để không sử dụng createdAt
- `student-debug.html` - Template debug mới

## So sánh với Teacher

| Vấn đề | Teacher | Student |
|--------|---------|---------|
| Template lỗi | ✅ Đã sửa | ✅ Đã sửa |
| Error handling | ✅ Đã sửa | ✅ Đã sửa |
| Debug logging | ✅ Đã sửa | ✅ Đã sửa |
| Debug endpoint | ✅ Đã sửa | ✅ Đã sửa |

## Hướng dẫn tiếp theo

1. **Test kỹ lưỡng**: Test tất cả các trường hợp navigation cho cả teacher và student
2. **Monitor logs**: Kiểm tra console logs để debug
3. **Session management**: Kiểm tra cấu hình session nếu vấn đề vẫn còn
4. **Database connection**: Kiểm tra kết nối database nếu cần

## Debug endpoints
- Teacher: `http://localhost:8080/teacher/debug`
- Student: `http://localhost:8080/student/debug`

Cả hai endpoint này sẽ hiển thị thông tin chi tiết về authentication và user data để debug. 