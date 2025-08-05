-- Script khởi tạo dữ liệu cho hệ thống lịch học

-- ========================================
-- PHÒNG HỌC (ROOMS)
-- ========================================

INSERT INTO rooms (room_code, room_name, capacity, room_type, building, floor, equipment, active) VALUES
-- Phòng LAB (Thực hành máy tính)
('LAB1', 'Phòng thực hành LAB 1', 30, 'LAB', 'Tòa A', 1, 'Máy tính, máy chiếu, board', true),
('LAB2', 'Phòng thực hành LAB 2', 30, 'LAB', 'Tòa A', 1, 'Máy tính, máy chiếu, board', true),
('LAB3', 'Phòng thực hành LAB 3', 25, 'LAB', 'Tòa A', 2, 'Máy tính, máy chiếu, board', true),

-- Phòng học lý thuyết
('101', 'Phòng học 101', 40, 'CLASSROOM', 'Tòa B', 1, 'Máy chiếu, board, âm thanh', true),
('201', 'Phòng học 201', 45, 'CLASSROOM', 'Tòa B', 2, 'Máy chiếu, board, âm thanh', true),
('301', 'Phòng học 301', 50, 'CLASSROOM', 'Tòa B', 3, 'Máy chiếu, board, âm thanh', true),
('205', 'Phòng học 205', 35, 'CLASSROOM', 'Tòa B', 2, 'Máy chiếu, board', true),

-- Phòng hội thảo lớn
('HALL1', 'Hội trường A', 100, 'LECTURE_HALL', 'Tòa C', 1, 'Hệ thống âm thanh chuyên nghiệp, máy chiếu lớn', true),
('HALL2', 'Hội trường B', 80, 'LECTURE_HALL', 'Tòa C', 2, 'Hệ thống âm thanh, máy chiếu', true);

-- ========================================
-- KHUNG GIỜ HỌC (TIME_SLOTS)
-- ========================================

INSERT INTO time_slots (period_number, start_time, end_time, period_name, is_break, active) VALUES
-- Tiết học buổi sáng
(1, '07:00', '07:45', 'Tiết 1', false, true),
(2, '07:45', '08:30', 'Tiết 2', false, true),

-- Giờ nghỉ sáng
(99, '08:30', '08:45', 'Nghỉ giải lao', true, true),

-- Tiếp tục tiết sáng
(3, '08:45', '09:30', 'Tiết 3', false, true),
(4, '09:30', '10:15', 'Tiết 4', false, true),

-- Nghỉ trưa
(98, '10:15', '13:30', 'Nghỉ trưa', true, true),

-- Tiết học buổi chiều
(5, '13:30', '14:15', 'Tiết 5', false, true),
(6, '14:15', '15:00', 'Tiết 6', false, true),
(7, '15:00', '15:45', 'Tiết 7', false, true),
(8, '15:45', '16:30', 'Tiết 8', false, true);

-- ========================================
-- KỲ ĐĂNG KÝ MÔN HỌC (REGISTRATION_PERIODS)
-- ========================================

INSERT INTO registration_periods (period_name, start_time, end_time, description, max_subjects_per_student, max_credits_per_student, status, active) VALUES
('Đăng ký môn học Kỳ 1 năm 2025', '2025-08-01 08:00:00', '2025-08-15 17:00:00', 
 'Kỳ đăng ký môn học cho học kỳ 1 năm học 2025-2026. Sinh viên có thể đăng ký tối đa 8 môn học và 24 tín chỉ.', 
 8, 24, 'OPEN', true),

('Đăng ký môn học Kỳ 2 năm 2025', '2025-12-01 08:00:00', '2025-12-15 17:00:00', 
 'Kỳ đăng ký môn học cho học kỳ 2 năm học 2025-2026. Sinh viên có thể đăng ký tối đa 8 môn học và 24 tín chỉ.', 
 8, 24, 'SCHEDULED', true),

('Đăng ký môn học Hè 2025', '2025-04-01 08:00:00', '2025-04-10 17:00:00', 
 'Kỳ đăng ký môn học hè năm 2025. Sinh viên có thể đăng ký tối đa 4 môn học và 12 tín chỉ.', 
 4, 12, 'CLOSED', true);

-- ========================================
-- LỊCH HỌC MẪU (SCHEDULES)
-- ========================================
-- Lưu ý: Cần có dữ liệu trong bảng classes, subjects, users (teachers) trước

-- Lịch cho Java Web Development (class_id = 1)
-- Giả sử có teacher với id = 2, subjects với id tương ứng

-- Thứ 2: Java Programming
INSERT INTO schedules (class_id, subject_id, teacher_id, room_id, time_slot_id, day_of_week, start_date, end_date, status, notes, created_at, updated_at, active)
SELECT 1, 1, 2, 2, 1, 1, '2025-08-18', '2025-12-20', 'CONFIRMED', 'Lịch học Java Programming - Thứ 2 tiết 1-2', NOW(), NOW(), true
WHERE EXISTS (SELECT 1 FROM classes WHERE id = 1) 
AND EXISTS (SELECT 1 FROM subjects WHERE id = 1)
AND EXISTS (SELECT 1 FROM users WHERE id = 2)
AND EXISTS (SELECT 1 FROM rooms WHERE id = 2)
AND EXISTS (SELECT 1 FROM time_slots WHERE id = 1);

INSERT INTO schedules (class_id, subject_id, teacher_id, room_id, time_slot_id, day_of_week, start_date, end_date, status, notes, created_at, updated_at, active)
SELECT 1, 1, 2, 2, 2, 1, '2025-08-18', '2025-12-20', 'CONFIRMED', 'Lịch học Java Programming - Thứ 2 tiết 2', NOW(), NOW(), true
WHERE EXISTS (SELECT 1 FROM classes WHERE id = 1) 
AND EXISTS (SELECT 1 FROM subjects WHERE id = 1)
AND EXISTS (SELECT 1 FROM users WHERE id = 2)
AND EXISTS (SELECT 1 FROM rooms WHERE id = 2)
AND EXISTS (SELECT 1 FROM time_slots WHERE id = 2);

-- Thứ 3: Database
INSERT INTO schedules (class_id, subject_id, teacher_id, room_id, time_slot_id, day_of_week, start_date, end_date, status, notes, created_at, updated_at, active)
SELECT 1, 2, 3, 4, 3, 2, '2025-08-19', '2025-12-20', 'CONFIRMED', 'Lịch học Database - Thứ 3 tiết 3-4', NOW(), NOW(), true
WHERE EXISTS (SELECT 1 FROM classes WHERE id = 1) 
AND EXISTS (SELECT 1 FROM subjects WHERE id = 2)
AND EXISTS (SELECT 1 FROM users WHERE id = 3)
AND EXISTS (SELECT 1 FROM rooms WHERE id = 4)
AND EXISTS (SELECT 1 FROM time_slots WHERE id = 3);

INSERT INTO schedules (class_id, subject_id, teacher_id, room_id, time_slot_id, day_of_week, start_date, end_date, status, notes, created_at, updated_at, active)
SELECT 1, 2, 3, 4, 4, 2, '2025-08-19', '2025-12-20', 'CONFIRMED', 'Lịch học Database - Thứ 3 tiết 4', NOW(), NOW(), true
WHERE EXISTS (SELECT 1 FROM classes WHERE id = 1) 
AND EXISTS (SELECT 1 FROM subjects WHERE id = 2)
AND EXISTS (SELECT 1 FROM users WHERE id = 3)
AND EXISTS (SELECT 1 FROM rooms WHERE id = 4)
AND EXISTS (SELECT 1 FROM time_slots WHERE id = 4);

-- Thứ 4: Web Development  
INSERT INTO schedules (class_id, subject_id, teacher_id, room_id, time_slot_id, day_of_week, start_date, end_date, status, notes, created_at, updated_at, active)
SELECT 1, 3, 2, 1, 1, 3, '2025-08-20', '2025-12-20', 'CONFIRMED', 'Lịch học Web Development - Thứ 4 tiết 1-2', NOW(), NOW(), true
WHERE EXISTS (SELECT 1 FROM classes WHERE id = 1) 
AND EXISTS (SELECT 1 FROM subjects WHERE id = 3)
AND EXISTS (SELECT 1 FROM users WHERE id = 2)
AND EXISTS (SELECT 1 FROM rooms WHERE id = 1)
AND EXISTS (SELECT 1 FROM time_slots WHERE id = 1);

INSERT INTO schedules (class_id, subject_id, teacher_id, room_id, time_slot_id, day_of_week, start_date, end_date, status, notes, created_at, updated_at, active)
SELECT 1, 3, 2, 1, 2, 3, '2025-08-20', '2025-12-20', 'CONFIRMED', 'Lịch học Web Development - Thứ 4 tiết 2', NOW(), NOW(), true
WHERE EXISTS (SELECT 1 FROM classes WHERE id = 1) 
AND EXISTS (SELECT 1 FROM subjects WHERE id = 3)
AND EXISTS (SELECT 1 FROM users WHERE id = 2)
AND EXISTS (SELECT 1 FROM rooms WHERE id = 1)
AND EXISTS (SELECT 1 FROM time_slots WHERE id = 2);

-- Thứ 5: Spring Framework
INSERT INTO schedules (class_id, subject_id, teacher_id, room_id, time_slot_id, day_of_week, start_date, end_date, status, notes, created_at, updated_at, active)
SELECT 1, 4, 3, 3, 5, 4, '2025-08-21', '2025-12-20', 'CONFIRMED', 'Lịch học Spring Framework - Thứ 5 tiết 5-6', NOW(), NOW(), true
WHERE EXISTS (SELECT 1 FROM classes WHERE id = 1) 
AND EXISTS (SELECT 1 FROM subjects WHERE id = 4)
AND EXISTS (SELECT 1 FROM users WHERE id = 3)
AND EXISTS (SELECT 1 FROM rooms WHERE id = 3)
AND EXISTS (SELECT 1 FROM time_slots WHERE id = 5);

INSERT INTO schedules (class_id, subject_id, teacher_id, room_id, time_slot_id, day_of_week, start_date, end_date, status, notes, created_at, updated_at, active)
SELECT 1, 4, 3, 3, 6, 4, '2025-08-21', '2025-12-20', 'CONFIRMED', 'Lịch học Spring Framework - Thứ 5 tiết 6', NOW(), NOW(), true
WHERE EXISTS (SELECT 1 FROM classes WHERE id = 1) 
AND EXISTS (SELECT 1 FROM subjects WHERE id = 4)
AND EXISTS (SELECT 1 FROM users WHERE id = 3)
AND EXISTS (SELECT 1 FROM rooms WHERE id = 3)
AND EXISTS (SELECT 1 FROM time_slots WHERE id = 6);

-- Thứ 6: Project
INSERT INTO schedules (class_id, subject_id, teacher_id, room_id, time_slot_id, day_of_week, start_date, end_date, status, notes, created_at, updated_at, active)
SELECT 1, 5, 2, 2, 3, 5, '2025-08-22', '2025-12-20', 'CONFIRMED', 'Lịch học Project - Thứ 6 tiết 3-4', NOW(), NOW(), true
WHERE EXISTS (SELECT 1 FROM classes WHERE id = 1) 
AND EXISTS (SELECT 1 FROM subjects WHERE id = 5)
AND EXISTS (SELECT 1 FROM users WHERE id = 2)
AND EXISTS (SELECT 1 FROM rooms WHERE id = 2)
AND EXISTS (SELECT 1 FROM time_slots WHERE id = 3);

INSERT INTO schedules (class_id, subject_id, teacher_id, room_id, time_slot_id, day_of_week, start_date, end_date, status, notes, created_at, updated_at, active)
SELECT 1, 5, 2, 2, 4, 5, '2025-08-22', '2025-12-20', 'CONFIRMED', 'Lịch học Project - Thứ 6 tiết 4', NOW(), NOW(), true
WHERE EXISTS (SELECT 1 FROM classes WHERE id = 1) 
AND EXISTS (SELECT 1 FROM subjects WHERE id = 5)
AND EXISTS (SELECT 1 FROM users WHERE id = 2)
AND EXISTS (SELECT 1 FROM rooms WHERE id = 2)
AND EXISTS (SELECT 1 FROM time_slots WHERE id = 4);

-- ========================================
-- ĐĂNG KÝ MÔN HỌC CỦA SINH VIÊN (STUDENT_REGISTRATIONS)
-- ========================================
-- Sẽ được tạo thông qua giao diện đăng ký của sinh viên

-- Thông báo hoàn thành
SELECT 'Dữ liệu mẫu cho hệ thống lịch học đã được khởi tạo thành công!' as message;
