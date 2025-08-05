# Sửa lỗi Teacher Profile

## Vấn đề
- Khi teacher đăng nhập lần đầu thì có thể xem profile
- Khi navigate sang trang khác rồi quay lại thì trang profile bị trống (khoảng trắng)
- URL: `http://localhost:8080/teacher/profile`

## Nguyên nhân có thể
1. **Template lỗi**: Sử dụng `teacher.createdAt` nhưng User model không có field này
2. **Authentication session**: Có thể session bị mất hoặc authentication không ổn định
3. **Database connection**: Có thể có vấn đề với việc lấy dữ liệu từ database

## Giải pháp đã áp dụng

### 1. Sửa Template
- **Vấn đề**: Template sử dụng `${teacher.createdAt}` nhưng User model không có field này
- **Giải pháp**: Thay thế bằng thông tin trạng thái hoạt động
```html
<!-- Trước -->
<span th:text="${#temporals.format(teacher.createdAt, 'dd/MM/yyyy HH:mm')}"></span>

<!-- Sau -->
<span class="badge bg-success" th:if="${teacher.enabled}">Hoạt động</span>
<span class="badge bg-danger" th:unless="${teacher.enabled}">Không hoạt động</span>
```

### 2. Cải thiện Error Handling
- **Vấn đề**: Khi không tìm thấy teacher, redirect đến login
- **Giải pháp**: Hiển thị lỗi và tạo teacher mẫu để template không bị lỗi
```java
@GetMapping("/profile")
public String showTeacherProfile(Model model) {
    User teacher = getCurrentTeacher();
    if (teacher == null) {
        model.addAttribute("error", "Không tìm thấy thông tin giảng viên! Vui lòng đăng nhập lại.");
        // Thêm một teacher mẫu để template không bị lỗi
        teacher = new User();
        teacher.setUsername("Unknown");
        teacher.setFullName("Không xác định");
        teacher.setEmail("");
        teacher.setPhone("");
    }

    model.addAttribute("teacher", teacher);
    return "teacher/teacher-profile";
}
```

### 3. Thêm Debug Logging
- Thêm logging để debug vấn đề authentication
- Thêm endpoint `/teacher/debug` để kiểm tra thông tin

### 4. Cải thiện getCurrentTeacher()
```java
private User getCurrentTeacher() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
        System.out.println("DEBUG: Authentication is null");
        return null;
    }
    
    String username = auth.getName();
    System.out.println("DEBUG: Current username: " + username);
    
    User teacher = userService.findByUsername(username).orElse(null);
    if (teacher == null) {
        System.out.println("DEBUG: Teacher not found for username: " + username);
    } else {
        System.out.println("DEBUG: Teacher found: " + teacher.getFullName() + " (ID: " + teacher.getId() + ")");
    }
    
    return teacher;
}
```

## Testing

### 1. Test cơ bản
1. Đăng nhập với tài khoản teacher
2. Truy cập `/teacher/profile`
3. Kiểm tra xem thông tin có hiển thị đúng không

### 2. Test navigation
1. Đăng nhập với tài khoản teacher
2. Truy cập `/teacher/profile`
3. Click vào "Lớp đang dạy" trong sidebar
4. Click vào "Tài khoản cá nhân" trong sidebar
5. Kiểm tra xem profile có hiển thị đúng không

### 3. Test debug
1. Truy cập `/teacher/debug`
2. Kiểm tra thông tin authentication và teacher
3. Sử dụng thông tin này để debug nếu có vấn đề

## Files đã sửa

### Controllers
- `TeacherController.java` - Cải thiện error handling và thêm debug

### Templates
- `teacher-profile.html` - Sửa template để không sử dụng createdAt
- `teacher-debug.html` - Template debug mới

## Hướng dẫn tiếp theo

1. **Test kỹ lưỡng**: Test tất cả các trường hợp navigation
2. **Monitor logs**: Kiểm tra console logs để debug
3. **Session management**: Kiểm tra cấu hình session nếu vấn đề vẫn còn
4. **Database connection**: Kiểm tra kết nối database nếu cần

## Debug endpoint
Truy cập `http://localhost:8080/teacher/debug` để xem thông tin chi tiết về authentication và teacher data. 