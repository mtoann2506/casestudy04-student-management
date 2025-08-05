# Debug Student Navigation Issue

## Vấn đề
- Khi đăng nhập lần đầu, student dashboard hiển thị bình thường
- Khi navigate từ các trang khác (profile, classes, grades, schedule) về dashboard, trang bị trống
- URL: `http://localhost:8080/student/dashboard`

## Nguyên nhân có thể
1. **Session bị mất** khi navigate giữa các trang
2. **Authentication context** bị reset
3. **Template cache** có vấn đề
4. **Anonymous user** khi navigate

## Giải pháp đã áp dụng

### 1. Cải thiện Authentication Check
```java
private User getCurrentStudent() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
        System.out.println("DEBUG: Authentication is null");
        return null;
    }
    
    String username = auth.getName();
    System.out.println("DEBUG: Current username: " + username);
    
    // Kiểm tra nếu username là "anonymousUser" hoặc null
    if (username == null || username.equals("anonymousUser")) {
        System.out.println("DEBUG: Username is anonymousUser or null");
        return null;
    }
    
    User student = userService.findByUsername(username).orElse(null);
    // ... rest of the code
}
```

### 2. Thêm Session Check Endpoint
```java
@GetMapping("/session")
public String checkSession(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User student = getCurrentStudent();
    
    System.out.println("DEBUG: Session check - Auth: " + (auth != null ? auth.getName() : "null"));
    System.out.println("DEBUG: Session check - Student: " + (student != null ? student.getFullName() : "null"));
    
    // ... add attributes to model
    return "student/student-session";
}
```

### 3. Cải thiện Dashboard Method
```java
@GetMapping("/dashboard")
public String showStudentDashboard(Model model) {
    System.out.println("DEBUG: Entering showStudentDashboard");
    
    try {
        // Kiểm tra authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("DEBUG: Authentication: " + (auth != null ? auth.getName() : "null"));
        
        User student = getCurrentStudent();
        // ... rest of the code
    } catch (Exception e) {
        // ... error handling
    }
}
```

## Cách debug

### 1. Test Navigation Flow
1. Đăng nhập với tài khoản student
2. Truy cập `/student/dashboard` (sẽ hoạt động)
3. Truy cập `/student/profile`
4. Click vào "Trang chủ" trong sidebar
5. Kiểm tra xem dashboard có hiển thị không

### 2. Kiểm tra Session
1. Truy cập `/student/session` để xem thông tin authentication
2. Kiểm tra console logs để xem debug messages
3. Sử dụng "Check Session" button trong debug info

### 3. Kiểm tra Console Logs
Chạy ứng dụng và kiểm tra:
- `DEBUG: Authentication: [username]`
- `DEBUG: Student object: [student name]`
- `DEBUG: Username is anonymousUser or null` (nếu có)

## Các nguyên nhân có thể

### 1. Session Timeout
- Session có thể bị timeout khi navigate
- Kiểm tra session configuration

### 2. Authentication Context
- Authentication context có thể bị reset
- Kiểm tra Spring Security configuration

### 3. Template Cache
- Template có thể bị cache với data cũ
- Kiểm tra Thymeleaf cache settings

### 4. Anonymous User
- Có thể chuyển thành anonymous user khi navigate
- Kiểm tra security configuration

## Hướng dẫn tiếp theo

1. **Test navigation flow** theo hướng dẫn trên
2. **Kiểm tra session**: Truy cập `/student/session`
3. **Monitor console logs** để xem debug messages
4. **Kiểm tra security configuration** nếu cần

## Files đã sửa

### Controllers
- `StudentController.java` - Cải thiện authentication check và thêm session endpoint

### Templates
- `student-panel.html` - Cải thiện debug info
- `student-session.html` - Template mới để kiểm tra session

## Debug Endpoints
- `/student/session` - Kiểm tra session và authentication
- `/student/debug` - Debug thông tin chi tiết
- `/student/test` - Test endpoint đơn giản

## Kết quả mong đợi
Sau khi debug, sẽ xác định được:
- Nguyên nhân chính xác của navigation issue
- Cách sửa session/authentication problem
- Đảm bảo dashboard hoạt động ổn định khi navigate 