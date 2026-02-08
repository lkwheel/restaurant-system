CREATE DATABASE IF NOT EXISTS user_db;
USE user_db;

CREATE TABLE IF NOT EXISTS user
(
    user_id
                  BIGINT
        AUTO_INCREMENT
        PRIMARY
            KEY,
    user_name
                  VARCHAR(255) NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    city          VARCHAR(255),
    address       VARCHAR(255)
);

INSERT INTO user (user_id, user_name, user_password, city, address)
VALUES (1, 'dummy', 'pass123', 'Bowie', '123 Main Street')