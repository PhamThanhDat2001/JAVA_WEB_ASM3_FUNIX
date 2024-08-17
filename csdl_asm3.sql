DROP DATABASE IF EXISTS doctorcare;
CREATE DATABASE doctorcare;
USE doctorcare;

CREATE TABLE roles (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME
);

CREATE TABLE users (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255),
    email VARCHAR(255),
    `password` VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255),
    avatar VARCHAR(255),
    gender VARCHAR(255),
    `description` TEXT,
    role_id INT(11),
    is_active BOOLEAN,
    training_process VARCHAR(255),
    achievement VARCHAR(255),
    reason VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME,
    reset_token VARCHAR(255),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE specializations (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255),
    `description` TEXT,
    image VARCHAR(255),
    consultation_cost DECIMAL(10, 2),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME
);

CREATE TABLE clinics (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    `namextra_infose` VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255),
    introduction_html TEXT,
    introduction_markdown TEXT,
    `description` TEXT,
    image VARCHAR(255),
    working_hours VARCHAR(255),
    important_notes VARCHAR(255),
    consultation_cost DECIMAL(10, 2),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME
);


CREATE TABLE doctor_users (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    doctor_id INT(11),
    clinic_id  INT(11),
    specialization_id  INT(11),	
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME,
    FOREIGN KEY (doctor_id) REFERENCES users(id),
    FOREIGN KEY (clinic_id) REFERENCES clinics(id),
    FOREIGN KEY (specialization_id) REFERENCES specializations(id)
);
CREATE TABLE schedules (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    doctorId INT(11),
    `date` VARCHAR(255),
    `time` VARCHAR(255),
    maxBooking VARCHAR(255),
    sumBooking VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME,
    FOREIGN KEY (doctorId) REFERENCES doctor_users(id)
);

CREATE TABLE statuses (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME
);

CREATE TABLE patients (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    doctor_id INT(11),
    status_id INT(11),
    `name` VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME,
    FOREIGN KEY (doctor_id) REFERENCES doctor_users(id),
    FOREIGN KEY (status_id) REFERENCES statuses(id)
);



CREATE TABLE places (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME
);

CREATE TABLE extrainfos (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    patient_id INT(11),
    historyBreath TEXT,
    place_id INT(11),
    oldForms TEXT,
    sendForms TEXT,
    moreInfo TEXT,
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME,
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (place_id) REFERENCES places(id)
);

CREATE TABLE posts (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content_markdown TEXT,
    content_html TEXT,
    doctor_id INT(11),
    specialization_id INT(11),
    clinic_id INT(11),
    writer_id INT(11),
    confirm_by_doctor TINYINT(1),
    image VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME,
    FOREIGN KEY (doctor_id) REFERENCES doctor_users(id),
    FOREIGN KEY (specialization_id) REFERENCES specializations(id),
    FOREIGN KEY (clinic_id) REFERENCES clinics(id),
    FOREIGN KEY (writer_id) REFERENCES users(id)
);

CREATE TABLE comments (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    doctorId INT(11),
    timeBooking VARCHAR(255),
    dateBooking VARCHAR(255),
    `name` VARCHAR(255),
    phone VARCHAR(255),
    content TEXT,
    `status` TINYINT(1),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME,
    FOREIGN KEY (doctorId) REFERENCES doctor_users(id)
);

CREATE TABLE sessions (
    sid VARCHAR(36) PRIMARY KEY,
    expires DATETIME,
    `data` TEXT,
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME
);

CREATE TABLE supporterlogs (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    patient_id INT(11),
    supporter_id INT(11),
    content VARCHAR(255),
    created_at DATETIME,
    updated_at DATETIME,
    deleted_at DATETIME,
    FOREIGN KEY (patient_id) REFERENCES patients(id),
    FOREIGN KEY (supporter_id) REFERENCES users(id)
);



CREATE TABLE sequelizemeta (
    `name` VARCHAR(255) PRIMARY KEY
);
CREATE TABLE appointments (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    specialization_id INT(11),
    clinic_id INT(11),
    user_id INT(11),
	`date` DATETIME,
    FOREIGN KEY (specialization_id) REFERENCES specializations(id),
    FOREIGN KEY (clinic_id) REFERENCES clinics(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE appointments_sche (
    id INT(11) AUTO_INCREMENT PRIMARY KEY,
    specialization_id INT(11),
    clinic_id INT(11),
    doctor_id INT(11),
    user_id INT(11),
	`date` DATE,
    consultation_cost DECIMAL(10,2),
    `name` VARCHAR(255),
    `date_time` DATETIME,
	gender VARCHAR(255),
    phone VARCHAR(255),
    address VARCHAR(255),
    note VARCHAR(255),
    status_id INT(11),
    reason VARCHAR(255),
    FOREIGN KEY (specialization_id) REFERENCES specializations(id),
    FOREIGN KEY (clinic_id) REFERENCES clinics(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (doctor_id) REFERENCES users(id),
    FOREIGN KEY (status_id) REFERENCES statuses(id)
);

CREATE TABLE clinic_specializations (
    clinic_id INT,
    specialization_id INT,
    PRIMARY KEY (clinic_id, specialization_id),
    FOREIGN KEY (clinic_id) REFERENCES clinics(id),
    FOREIGN KEY (specialization_id) REFERENCES specializations(id)
);



-- Insert data into roles
INSERT INTO roles (`name`, created_at, updated_at, deleted_at) VALUES
('ROLE_ADMIN', NOW(), NOW(), NULL),
('ROLE_DOCTOR', NOW(), NOW(), NULL),
('ROLE_Nurse', NOW(), NOW(), NULL),
('ROLE_PATIENT', NOW(), NOW(), NULL),
('ROLE_Support', NOW(), NOW(), NULL);

-- Insert data into users
-- pass: 123456
INSERT INTO users (`name`, email, `password`, address, phone, avatar, gender, `description`, role_id, is_active, training_process, achievement, reason, created_at, updated_at, deleted_at) VALUES
('Nguyễn Văn A', 'vana@gmail.com', '$2a$12$DXEH11r1ll0lL5Wnd/efOuoWp9O3NQhSUOLMpI9n4xo2WcPdp0Dz6', 'Hà Nội', '0901234567', 'https://taimuihongsg.com/wp-content/uploads/2023/10/BS-TRUONG-CONG-TRANG-KHOA-CHAN-DOAN-HINH-ANH_taimuihongsg.jpg', 'Nam', 'Bác sĩ ', 2, true, "qtdt", "ttdt", null, NOW(), NOW(), NULL),
('Nguyễn Văn Q', 'vanq@gmail.com', '$2a$12$DXEH11r1ll0lL5Wnd/efOuoWp9O3NQhSUOLMpI9n4xo2WcPdp0Dz6', 'Hà Nội', '0901234567', 'https://taimuihongsg.com/wp-content/uploads/2018/05/Kim-Bun-ThuongE_taimuihongsg.jpg', 'Nam', 'Bác sĩ ', 2, true, "qtdt", "ttdt", null, NOW(), NOW(), NULL),
('Trần Thị B', 'datptfx38455@funix.edu.vn', '$2a$12$DXEH11r1ll0lL5Wnd/efOuoWp9O3NQhSUOLMpI9n4xo2WcPdp0Dz6', 'Hồ Chí Minh', '0912345678', 'ha.jpg', 'Nữ', ' khoa', 4, true, "qtdt", "ttdt", null, NOW(), NOW(), NULL),
('Lê Văn C', 'vanc@gmail.com', '$2a$12$DXEH11r1ll0lL5Wnd/efOuoWp9O3NQhSUOLMpI9n4xo2WcPdp0Dz6', 'Đà Nẵng', '0923456789', 'https://taimuihongsg.com/wp-content/uploads/2018/05/Lam-Thi-Ngoc-Bich_taimuihongsg.jpg', 'Nam', 'bs', 2, true, "qtdt", "ttdt", null, NOW(), NOW(), NULL),
('Lê Văn Y', 'vany@gmail.com', '$2a$12$DXEH11r1ll0lL5Wnd/efOuoWp9O3NQhSUOLMpI9n4xo2WcPdp0Dz6', 'Đà Nẵng', '0923456789', 'https://taimuihongsg.com/wp-content/uploads/2018/05/Nguyen-Thi-Bong_taimuihongsg.jpg', 'Nam', 'bs', 2, true, "qtdt", "ttdt", null, NOW(), NOW(), NULL),
('Phạm Thị D', 'thid@gmail.com', '$2a$12$DXEH11r1ll0lL5Wnd/efOuoWp9O3NQhSUOLMpI9n4xo2WcPdp0Dz6', 'Cần Thơ', '0934567890', 'avatar4.png', 'Nữ', 'Bệnh nhân', 4, true, "qtdt", "ttdt", null, NOW(), NOW(), NULL),
('Hoàng Văn E', 'vane@gmail.com', '$2a$12$DXEH11r1ll0lL5Wnd/efOuoWp9O3NQhSUOLMpI9n4xo2WcPdp0Dz6', 'Hải Phòng', '0945678901', 'avatar5.png', 'Nam', 'Bệnh nhân', 4, false, "qtdt", "ttdt", "bị ban", NOW(), NOW(), NULL),
('Admin', 'admin@gmail.com', '$2a$12$DXEH11r1ll0lL5Wnd/efOuoWp9O3NQhSUOLMpI9n4xo2WcPdp0Dz6', 'Đà Nẵng', '0923456789', 'admin.jpg', 'Nam', 'admin', 1, true, "qtdt", "ttdt", null, NOW(), NOW(), NULL);

-- Insert data into specializations
INSERT INTO specializations (`name`, `description`, image, consultation_cost, created_at, updated_at, deleted_at) VALUES
('Nội khoa', 'Chuyên khoa nội tổng hợp', 'https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSaMaJYrx_VWof0KBLJDLD9s1OZZGjP-eA8UfTqQMvuCN3_EVc8',500000 , NOW(), NOW(), NULL),
('Ngoại khoa', 'Chuyên khoa phẫu thuật', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAV0rbC8LZ9pc7QDVYf2qt_IRQb6Cs9XTSMw&s',600000 ,  NOW(), NOW(), NULL),
('Nhi khoa', 'Chuyên khoa cho trẻ em', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT3Hl2d2hxY6BIy6v_QL6nzf6nulQY-ZHvDpedcb-3xtuhY-4rQ',800000 ,  NOW(), NOW(), NULL),
('Da liễu', 'Chuyên khoa da liễu', 'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcS3HoYuMzNtZ9jB3gjZ6Vb-d09wJRX9iE5owzxB22ev6XcPXzpZ', 400000 , NOW(), NOW(), NULL),
('Tim mạch', 'Chuyên khoa tim mạch', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS0HYxBR_q_DU7nnnTH3Y2p1HVForhgR1S9juyLISY-dsuvTpO2',700000 ,  NOW(), NOW(), NULL);


-- Insert data into clinics
INSERT INTO clinics (
    namextra_infose, address, phone, introduction_html, introduction_markdown,
    description, image, working_hours, important_notes, consultation_cost, created_at, updated_at, deleted_at
) VALUES
('Bệnh viện Việt Đức', 'Hà Nội', '0901234567', '<p>Giới thiệu Bệnh viện Việt Đức</p>', 'Giới thiệu Bệnh viện Việt Đức',
 'Bệnh viện nổi tiếng với các chuyên khoa đa dạng', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTKOwLaS_SzUJPYvTnqYMimrYaj8Crr7JlKw&s', '08:00 - 17:00', 'Lưu ý: Đăng ký trước', 500000.00, NOW(), NOW(), NULL),
('Bệnh viện Chợ Rẫy', 'Hồ Chí Minh', '0912345678', '<p>Giới thiệu Bệnh viện Chợ Rẫy</p>', 'Giới thiệu Bệnh viện Chợ Rẫy',
 'Bệnh viện lớn, cung cấp dịch vụ y tế chuyên sâu', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRTXmsD3RRkGTQNnG6vxPe91PgIqfKJpPHkfw&s', '07:00 - 18:00', 'Lưu ý: Mang theo giấy tờ tùy thân', 600000.00, NOW(), NOW(), NULL),
('Bệnh viện Y Dược', 'Đà Nẵng', '0923456789', '<p>Giới thiệu Bệnh viện Đại học Y Dược</p>', 'Giới thiệu Bệnh viện Đại học Y Dược',
 'Cung cấp dịch vụ khám chữa bệnh với đội ngũ bác sĩ giỏi', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwmBKthCnfbOHGHMaSm32aSl0D4LZ-2og9WA&s', '08:00 - 17:00', 'Lưu ý: Đặt lịch trước qua điện thoại', 550000.00, NOW(), NOW(), NULL),
('Bệnh viện 115', 'Hồ Chí Minh', '0934567890', '<p>Giới thiệu Bệnh viện 115</p>', 'Giới thiệu Bệnh viện 115',
 'Chuyên cung cấp dịch vụ khám và điều trị nội khoa', 'https://benhvien115.com.vn/data/logo_footer_Benhviennhandan115.jpg', '08:00 - 16:00', 'Lưu ý: Đưa chứng minh nhân dân', 450000.00, NOW(), NOW(), NULL),
('Bệnh viện Hữu Nghị', 'Hải Phòng', '0945678901', '<p>Giới thiệu Bệnh viện Hữu Nghị</p>', 'Giới thiệu Bệnh viện Hữu Nghị',
 'Bệnh viện có các chuyên khoa và trang thiết bị hiện đại', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSJM_8jWBfrVqNMyxuh6JWLmsCexsotCmM_A&s', '08:00 - 17:00', 'Lưu ý: Có bảo hiểm y tế', 500000.00, NOW(), NOW(), NULL);

-- Insert data into doctor_users
INSERT INTO doctor_users (doctor_id, clinic_id, specialization_id, created_at, updated_at, deleted_at) VALUES
(1, 1, 1, NOW(), NOW(), NULL),
(2, 1, 1, NOW(), NOW(), NULL),
(5, 2, 2, NOW(), NOW(), NULL),
(4, 3, 2, NOW(), NOW(), NULL),
(1, 3, 3, NOW(), NOW(), NULL),
(4, 4, 3, NOW(), NOW(), NULL),	
(5, 5, 4, NOW(), NOW(), NULL),
(4, 5, 5, NOW(), NOW(), NULL);

-- Insert data into schedules
INSERT INTO schedules (doctorId, `date`, `time`, maxBooking, sumBooking, created_at, updated_at, deleted_at) VALUES
(1, '2024-07-20', '09:00', '10', '5', NOW(), NOW(), NULL),
(1, '2024-07-21', '10:00', '15', '10', NOW(), NOW(), NULL),
(2, '2024-07-22', '11:00', '20', '15', NOW(), NOW(), NULL),
(2, '2024-07-23', '13:00', '25', '20', NOW(), NOW(), NULL),
(2, '2024-07-24', '14:00', '30', '25', NOW(), NOW(), NULL);

-- Insert data into statuses
INSERT INTO statuses (`name`, created_at, updated_at, deleted_at) VALUES
('Chờ xác nhận', NOW(), NOW(), NULL),
('Đã xác nhận', NOW(), NOW(), NULL),
('Hủy bỏ', NOW(), NOW(), NULL);

-- Insert data into patients
INSERT INTO patients (doctor_id, status_id, `name`, created_at, updated_at, deleted_at) VALUES
(1, 1, 'Nguyễn Văn F', NOW(), NOW(), NULL),
(1, 2, 'Trần Thị G', NOW(), NOW(), NULL),
(1, 1, 'Lê Văn H', NOW(), NOW(), NULL),
(1, 2, 'Phạm Thị I', NOW(), NOW(), NULL),
(1, 3, 'Hoàng Văn J', NOW(), NOW(), NULL);

-- Insert data into places
INSERT INTO places (`name`, created_at, updated_at, deleted_at) VALUES
('Hà Nội', NOW(), NOW(), NULL),
('Hồ Chí Minh', NOW(), NOW(), NULL),
('Đà Nẵng', NOW(), NOW(), NULL),
('Cần Thơ', NOW(), NOW(), NULL),
('Hải Phòng', NOW(), NOW(), NULL);

-- Insert data into extrainfos
INSERT INTO extrainfos (patient_id, historyBreath, place_id, oldForms, sendForms, moreInfo, created_at, updated_at, deleted_at) VALUES
(1, 'Tiền sử bệnh phổi', 1, 'Đơn thuốc cũ', 'Đơn thuốc mới', 'Thông tin thêm', NOW(), NOW(), NULL),
(2, 'Tiền sử bệnh tim', 2, 'Đơn thuốc cũ', 'Đơn thuốc mới', 'Thông tin thêm', NOW(), NOW(), NULL),
(3, 'Tiền sử bệnh tiểu đường', 3, 'Đơn thuốc cũ', 'Đơn thuốc mới', 'Thông tin thêm', NOW(), NOW(), NULL),
(4, 'Tiền sử bệnh cao huyết áp', 4, 'Đơn thuốc cũ', 'Đơn thuốc mới', 'Thông tin thêm', NOW(), NOW(), NULL),
(5, 'Tiền sử bệnh dạ dày', 5, 'Đơn thuốc cũ', 'Đơn thuốc mới', 'Thông tin thêm', NOW(), NOW(), NULL);

-- Insert data into posts
INSERT INTO posts (title, content_markdown, content_html, doctor_id, specialization_id, clinic_id, writer_id, confirm_by_doctor, image, created_at, updated_at, deleted_at) VALUES
('Bài viết 1', 'Nội dung Markdown bài viết 1', '<p>Nội dung HTML bài viết 1</p>', 1, 1, 1, 1, 1, 'post1.png', NOW(), NOW(), NULL),
('Bài viết 2', 'Nội dung Markdown bài viết 2', '<p>Nội dung HTML bài viết 2</p>', 1, 2, 2, 2, 1, 'post2.png', NOW(), NOW(), NULL),
('Bài viết 3', 'Nội dung Markdown bài viết 3', '<p>Nội dung HTML bài viết 3</p>', 1, 3, 3, 3, 1, 'post3.png', NOW(), NOW(), NULL),
('Bài viết 4', 'Nội dung Markdown bài viết 4', '<p>Nội dung HTML bài viết 4</p>', 1, 4, 4, 4, 1, 'post4.png', NOW(), NOW(), NULL),
('Bài viết 5', 'Nội dung Markdown bài viết 5', '<p>Nội dung HTML bài viết 5</p>', 1, 5, 5, 5, 1, 'post5.png', NOW(), NOW(), NULL);

-- Insert data into comments
INSERT INTO comments (doctorId, timeBooking, dateBooking, `name`, phone, content, `status`, created_at, updated_at, deleted_at) VALUES
(1, '09:00', '2024-07-20', 'Nguyễn Văn K', '0901234567', 'Bình luận 1', 1, NOW(), NOW(), NULL),
(1, '10:00', '2024-07-21', 'Trần Thị L', '0912345678', 'Bình luận 2', 1, NOW(), NOW(), NULL),
(1, '11:00', '2024-07-22', 'Lê Văn M', '0923456789', 'Bình luận 3', 1, NOW(), NOW(), NULL),
(1, '13:00', '2024-07-23', 'Phạm Thị N', '0934567890', 'Bình luận 4', 1, NOW(), NOW(), NULL),
(1, '14:00', '2024-07-24', 'Hoàng Văn O', '0945678901', 'Bình luận 5', 1, NOW(), NOW(), NULL);

-- Insert data into sessions
INSERT INTO sessions (sid, expires, `data`, created_at, updated_at, deleted_at) VALUES
('sid1', '2024-07-30 12:00:00', 'data1', NOW(), NOW(), NULL),
('sid2', '2024-07-30 13:00:00', 'data2', NOW(), NOW(), NULL),
('sid3', '2024-07-30 14:00:00', 'data3', NOW(), NOW(), NULL),
('sid4', '2024-07-30 15:00:00', 'data4', NOW(), NOW(), NULL),
('sid5', '2024-07-30 16:00:00', 'data5', NOW(), NOW(), NULL);

-- Insert data into supporterlogs
INSERT INTO supporterlogs (patient_id, supporter_id, content, created_at, updated_at, deleted_at) VALUES
(1, 1, 'Nhật ký hỗ trợ 1', NOW(), NOW(), NULL),
(2, 2, 'Nhật ký hỗ trợ 2', NOW(), NOW(), NULL),
(3, 3, 'Nhật ký hỗ trợ 3', NOW(), NOW(), NULL),
(4, 4, 'Nhật ký hỗ trợ 4', NOW(), NOW(), NULL),
(5, 5, 'Nhật ký hỗ trợ 5', NOW(), NOW(), NULL);

-- Insert data into sequelizemeta
INSERT INTO sequelizemeta (`name`) VALUES
('meta1'),
('meta2'),
('meta3'),
('meta4'),
('meta5');
-- ALTER TABLE users ADD COLUMN reset_token VARCHAR(255);
  

INSERT INTO appointments (specialization_id, clinic_id, user_id, `date`)
VALUES 
    (2, 2, 3,'2024-07-25 10:00:00'),
    (2, 3, 3,'2024-07-26 11:00:00'),
    (1, 4, 6,'2024-07-27 14:00:00'),	
    (2, 4, 6,'2024-07-27 14:00:00'),
    (1, 4, 7,'2024-07-27 14:00:00');

INSERT INTO appointments_sche (specialization_id, clinic_id, doctor_id, user_id, `date`, 
consultation_cost, name, date_time, gender, phone, address, note,status_id, reason
) VALUES 
    (4, 2, 1, 3, '2024-07-25', 400000, 'Nguyen Van A', '2024-07-25 10:00:00', 'Male', '0123456789', '123 Example Street', 'Initial consultation',1,null),
    (4, 2, 1, 3, '2024-07-25', 400000, 'Nguyen AA', '2024-07-25 10:00:00', 'Male', '0123456789', '123 Example Street', 'Initial consultation',2,null),
    (2, 3, 2, 3, '2024-07-26 ', 600000, 'Le Thi B', '2024-07-26 11:00:00', 'Female', '0987654321', '456 Another Street', 'Follow-up visit',1,null),
    (2, 3, 2, 3, '2024-07-26 ', 600000, 'Le BB', '2024-07-26 11:00:00', 'Female', '0987654321', '456 Another Street', 'Follow-up visit',2,null),
    (1, 4, 2, 6, '2024-07-27', 500000, 'Tran Van C', '2024-07-27 14:00:00', 'Male', '0111222333', '789 Some Street', 'Routine check-up',2,null),
    (2, 4, 4, 6, '2024-07-27 ', 600000, 'Pham Thi D', '2024-07-27 14:00:00', 'Female', '0222333444', '321 Different Street', 'Specialist consultation',3,null),
    (1, 4, 5, 7, '2024-07-27 ', 500000, 'Hoang Van E', '2024-07-27 14:00:00', 'Male', '0333444555', '654 Another Street', 'Annual check-up',2,null),
    (5, 4, 5, 7, '2024-07-27 ', 700000, 'Hoang Van Eff', '2024-07-27 14:00:00', 'Male', '0333444555', '654 Another Street', 'Annual check-up',2,"ban");


-- Liên kết Phòng khám A với các chuyên khoa
INSERT INTO clinic_specializations (clinic_id, specialization_id) VALUES
(1, 1), -- Phòng khám A với Nội khoa
(1, 2); -- Phòng khám A với Ngoại khoa
-- Liên kết Phòng khám B với các chuyên khoa
INSERT INTO clinic_specializations (clinic_id, specialization_id) VALUES
(2, 2); -- Phòng khám B với Ngoại khoa
-- Liên kết Phòng khám C với các chuyên khoa
INSERT INTO clinic_specializations (clinic_id, specialization_id) VALUES
(3, 3); -- Phòng khám C với Nhi khoa
-- Liên kết Phòng khám D với các chuyên khoa
INSERT INTO clinic_specializations (clinic_id, specialization_id) VALUES
(4, 4); -- Phòng khám D với Da liễu
-- Liên kết Phòng khám E với các chuyên khoa
INSERT INTO clinic_specializations (clinic_id, specialization_id) VALUES
(5, 5); -- Phòng khám E với Tim mạch

