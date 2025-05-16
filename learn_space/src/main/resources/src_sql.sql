-- XÓA CÁC BẢNG THEO THỨ TỰ PHỤ THUỘC
DROP TABLE IF EXISTS Materials;
DROP TABLE IF EXISTS Post;
DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS ClassRoom;
DROP TABLE IF EXISTS User;

-- TẠO LẠI BẢNG USER
CREATE TABLE User (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(255),
                      email VARCHAR(255) UNIQUE,
                      role INT,
                      username VARCHAR(100) UNIQUE,
                      password VARCHAR(255),
                      locked TINYINT
);

-- TẠO LẠI BẢNG CLASSROOM
CREATE TABLE ClassRoom (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           code VARCHAR(50) UNIQUE,
                           owner INT,
                           ative BOOLEAN,
                           createAt DATE,
                           FOREIGN KEY (owner) REFERENCES User(id)
);

-- TẠO LẠI BẢNG MEMBER
CREATE TABLE Member (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        idUser INT,
                        idClassRoom BIGINT,
                        joinAt DATE,
                        role VARCHAR(50),
                        FOREIGN KEY (idUser) REFERENCES User(id),
                        FOREIGN KEY (idClassRoom) REFERENCES ClassRoom(id)
);

-- TẠO LẠI BẢNG POST
CREATE TABLE Post (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      classId BIGINT,
                      author INT,
                      content VARCHAR(1000),
                      createAt DATE,
                      FOREIGN KEY (classId) REFERENCES ClassRoom(id),
                      FOREIGN KEY (author) REFERENCES User(id)
);

-- TẠO LẠI BẢNG MATERIALS
CREATE TABLE Materials (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           link VARCHAR(255),
                           idPost INT,
                           type VARCHAR(50),
                           FOREIGN KEY (idPost) REFERENCES Post(id)
);