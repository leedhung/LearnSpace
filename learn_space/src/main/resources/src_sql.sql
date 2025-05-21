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
                      role INT,
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
