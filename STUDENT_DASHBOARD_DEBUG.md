# Debug Student Dashboard

## Vấn đề hiện tại
- `http://localhost:8080/student/dashboard` vẫn hiển thị trang trắng
- Cần debug để tìm nguyên nhân chính xác

## Các bước debug đã thực hiện

### 1. Thêm Debug Logging
```java
@GetMapping("/dashboard")
public String showStudentDashboard(Model model) {
    System.out.println("DEBUG: Entering showStudentDashboard");
    
    try {
        User student = getCurrentStudent();
        System.out.println("DEBUG: Student object: " + (student != null ? student.getFullName() : "null"));
        
        // ... rest of the code
        
        System.out.println("DEBUG: Returning student-panel template");
        return "student/student-panel";
        
    } catch (Exception e) {
        System.out.println("DEBUG: Exception in showStudentDashboard: " + e.getMessage());
        e.printStackTrace();
        // ... error handling
    }
}
```

### 2. Thêm Try-Catch
- Bọc toàn bộ logic trong try-catch để bắt lỗi
- Hiển thị error message chi tiết
- Tạo dummy data để template không bị lỗi

### 3. Cải thiện Template
- Thêm debug info trong template
- Sửa cách xử lý null cho tất cả các field
- Thêm debug info trong header

### 4. Thêm Test Endpoint
```java
@GetMapping("/test")
public String testStudent(Model model) {
    System.out.println("DEBUG: Test endpoint called");
    model.addAttribute("message", "Test endpoint working!");
    return "student/student-test";
}
```

## Cách debug

### 1. Kiểm tra Console Logs
Chạy ứng dụng và kiểm tra console logs để xem:
- Có vào được method `showStudentDashboard` không
- Student object có null không
- Có exception nào xảy ra không

### 2. Test Endpoints
- Truy cập `http://localhost:8080/student/test` để kiểm tra controller hoạt động
- Truy cập `http://localhost:8080/student/debug` để xem thông tin authentication

### 3. Kiểm tra Template
- Template có hiển thị debug info không
- Có error message nào hiển thị không

## Các nguyên nhân có thể

### 1. Database Connection
- Có thể `classService.findAll()` bị lỗi
- Kiểm tra database connection

### 2. Template Error
- Có thể template có lỗi syntax
- Kiểm tra Thymeleaf logs

### 3. Authentication Issue
- Có thể authentication bị mất
- Kiểm tra session

### 4. Service Issue
- Có thể service không được inject đúng
- Kiểm tra Spring context

## Hướng dẫn tiếp theo

1. **Chạy ứng dụng và kiểm tra logs**
2. **Truy cập test endpoint**: `http://localhost:8080/student/test`
3. **Truy cập debug endpoint**: `http://localhost:8080/student/debug`
4. **Kiểm tra console logs** để xem debug messages
5. **Kiểm tra template debug info** nếu có error

## Files đã sửa

### Controllers
- `StudentController.java` - Thêm debug logging và try-catch

### Templates
- `student-panel.html` - Thêm debug info và cải thiện null handling
- `student-test.html` - Template test mới

## Kết quả mong đợi
Sau khi debug, sẽ xác định được:
- Nguyên nhân chính xác của lỗi
- Cách sửa lỗi hiệu quả
- Đảm bảo student dashboard hoạt động ổn định 