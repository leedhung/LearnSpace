-- XÓA CÁC BẢNG THEO THỨ TỰ PHỤ THUỘC
DROP TABLE IF EXISTS Materials;
DROP TABLE IF EXISTS Lesson;
DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS ClassRoom;
DROP TABLE IF EXISTS User;

-- TẠO LẠI BẢNG USER
CREATE TABLE User (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255),
                      email VARCHAR(255) UNIQUE,
                      role VARCHAR(50),
                      username VARCHAR(100) UNIQUE,
                      password VARCHAR(255),
                      locked TINYINT
);

-- TẠO LẠI BẢNG CLASSROOM
CREATE TABLE ClassRoom (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           code VARCHAR(50) UNIQUE,
                           owner INT,
                           active BOOLEAN,
                           createAt DATE,
                           FOREIGN KEY (owner) REFERENCES User(id)
);

-- TẠO LẠI BẢNG MEMBER
CREATE TABLE Member (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        idUser INT,
                        idClassRoom INT,
                        joinAt DATE,
                        role VARCHAR(50),
                        FOREIGN KEY (idUser) REFERENCES User(id),
                        FOREIGN KEY (idClassRoom) REFERENCES ClassRoom(id)
);

-- TẠO LẠI BẢNG LESSON
CREATE TABLE Lesson (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        classId INT,
                        author INT,
                        name VARCHAR(50),
                        content VARCHAR(1000),
                        createAt DATE,
                        FOREIGN KEY (classId) REFERENCES ClassRoom(id),
                        FOREIGN KEY (author) REFERENCES User(id)
);

-- TẠO LẠI BẢNG MATERIALS
CREATE TABLE Materials (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           link VARCHAR(255),
                           idLesson INT,
                           type VARCHAR(50),
                           FOREIGN KEY (idLesson) REFERENCES Lesson(id)
);
-- Thêm dữ liệu vào bảng User
-- XÓA CÁC BẢNG THEO THỨ TỰ PHỤ THUỘC
DROP TABLE IF EXISTS materials;
DROP TABLE IF EXISTS lesson;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS classroom;
DROP TABLE IF EXISTS user;

-- TẠO LẠI BẢNG user
CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255),
                      email VARCHAR(255) UNIQUE,
                      role VARCHAR(50),
                      username VARCHAR(100) UNIQUE,
                      password VARCHAR(255),
                      locked TINYINT
);

-- TẠO LẠI BẢNG classroom
CREATE TABLE classroom (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           code VARCHAR(50) UNIQUE,
                           owner INT,
                           active BOOLEAN,
                           create_at DATE,
                           FOREIGN KEY (owner) REFERENCES user(id)
);

-- TẠO LẠI BẢNG member
CREATE TABLE member (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        user_id INT,
                        classroom_id INT,
                        join_at DATE,
                        role VARCHAR(50),
                        FOREIGN KEY (user_id) REFERENCES user(id),
                        FOREIGN KEY (classroom_id) REFERENCES classroom(id)
);

-- TẠO LẠI BẢNG lesson
CREATE TABLE lesson (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        classroom_id INT,
                        author INT,
                        content VARCHAR(1000),
                        create_at DATE,
                        FOREIGN KEY (classroom_id) REFERENCES classroom(id),
                        FOREIGN KEY (author) REFERENCES user(id)
);

-- TẠO LẠI BẢNG materials
CREATE TABLE materials (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           link VARCHAR(255),
                           lesson_id INT,
                           type VARCHAR(50),
                           FOREIGN KEY (lesson_id) REFERENCES lesson(id)
);

-- Thêm dữ liệu vào bảng user
INSERT INTO user (name, email, role, username, password, locked)
VALUES
    ('Nguyễn Văn A', 'a@example.com', 'STUDENT', 'nguyenvana', 'pass123', 0),
    ('Trần Thị B', 'b@example.com', 'TEACHER', 'tranthib', 'pass456', 0),
    ('Lê Văn C', 'c@example.com', 'TEACHER', 'levanc', 'pass789', 0);

-- Thêm dữ liệu vào bảng classroom
INSERT INTO classroom (code, owner, active, create_at)
VALUES
    ('CLS001', 1, TRUE, '2025-05-01'),
    ('CLS002', 1, TRUE, '2025-05-02');

-- Thêm dữ liệu vào bảng member
INSERT INTO member (user_id, classroom_id, join_at, role)
VALUES
    (2, 1, '2025-05-03', 'STUDENT'),
    (3, 1, '2025-05-03', 'STUDENT'),
    (1, 1, '2025-05-01', 'TEACHER'),
    (2, 2, '2025-05-04', 'STUDENT');

-- Thêm dữ liệu vào bảng lesson
INSERT INTO lesson (classroom_id, author, content, create_at)
VALUES
    (1, 1, "CSDL",'Bài học đầu tiên về SQL cơ bản', '2025-05-05'),
    (1, 1, "LTNC",'Giới thiệu về lập trình Java', '2025-05-06');

-- Thêm dữ liệu vào bảng materials
INSERT INTO materials (link, lesson_id, type)
VALUES
    ('https://example.com/sql-tutorial.pdf', 1, 'pdf'),
    ('https://example.com/java-intro.mp4', 2, 'video');
