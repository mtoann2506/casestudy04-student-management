# Sửa lỗi Routing - Admin Panel

## Vấn đề
Các đường dẫn sau bị lỗi và dẫn đến trang `/admin/grades`:
- `http://localhost:8080/admin/panel`
- `http://localhost:8080/subjects`
- `http://localhost:8080/admin/info`

## Nguyên nhân
Tất cả các template admin đều có sidebar với link `/admin/grades` nhưng controller không có endpoint này, dẫn đến lỗi 404.

## Giải pháp đã áp dụng

### 1. Thêm endpoint `/admin/grades` vào DashboardController
```java
@GetMapping("/admin/grades")
public String adminGrades() {
    return "redirect:/grades";
}
```

### 2. Kiểm tra các template có link `/admin/grades`
Các template sau có sidebar với link `/admin/grades`:
- `admin-panel.html`
- `admin-info.html`
- `admin-subject.html`
- `admin-subject-form.html`
- `admin-register.html`

### 3. Endpoint mapping hiện tại
- `/admin/panel` → `admin-panel.html` ✅
- `/admin/info` → `admin-info.html` ✅
- `/subjects` → `admin-subject.html` ✅
- `/admin/grades` → redirect to `/grades` ✅

## Testing
Sau khi sửa, các đường dẫn sau sẽ hoạt động đúng:
- `http://localhost:8080/admin/panel` → Trang chủ admin
- `http://localhost:8080/subjects` → Quản lý môn học
- `http://localhost:8080/admin/info` → Quản lý tài khoản
- `http://localhost:8080/admin/grades` → Redirect đến `/grades` (quản lý điểm)

## Files đã sửa
- `DashboardController.java` - Thêm endpoint `/admin/grades`

## Hướng dẫn test
1. Khởi động ứng dụng
2. Đăng nhập với tài khoản admin
3. Test các đường dẫn:
   - Click "Trang chủ" trong sidebar
   - Click "Môn học" trong sidebar
   - Click "Tài khoản" trong sidebar
   - Click "Điểm số" trong sidebar

Tất cả các link này giờ sẽ hoạt động đúng và không bị redirect sai. 