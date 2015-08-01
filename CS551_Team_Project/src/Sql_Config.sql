drop Database CS551_TEAM_PROJECT;

CREATE DATABASE CS551_TEAM_PROJECT;
use CS551_TEAM_PROJECT;
drop table CS551_TEAM_PROJECT.user if exists;
CREATE TABLE IF NOT EXISTS CS551_TEAM_PROJECT.user (
    user_id BIGINT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(50),
    phone_number VARCHAR(20) NOT NULL UNIQUE,

    image_file_url VARCHAR(1000),
    image_file_name VARCHAR(50),
    image_file_type VARCHAR(10),
    image_file_size BIGINT,

    PRIMARY KEY (user_id)
);
CREATE TABLE IF NOT EXISTS CS551_TEAM_PROJECT.step_RECORD(
    step INT NOT NULL,
    record_start_date TIMESTAMP NOT NULL,
    record_end_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    phone_number VARCHAR(20) NOT NULL,
	step_record_id BIGINT NOT NULL AUTO_INCREMENT,
    
    PRIMARY KEY (step_record_id),
    FOREIGN KEY (phone_number)
        REFERENCES  cs551_team_project.user(phone_number)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cs551_team_project.post (
    post_id BIGINT NOT NULL AUTO_INCREMENT,
    phone_number VARCHAR(20) NOT NULL,
    message VARCHAR(1000),

    image_file_url VARCHAR(1000),
    image_file_name VARCHAR(50),
    image_file_type VARCHAR(10),
    image_file_size BIGINT,

PostTime datetime ,
    PRIMARY KEY (post_id),
    FOREIGN KEY (phone_number)
        REFERENCES cs551_team_project.user (phone_number)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cs551_team_project.challen
ge (
    challenge_id BIGINT NOT NULL AUTO_INCREMENT,
    user1_phone_number VARCHAR(20) NOT NULL,
    user2_phone_number VARCHAR(20) NOT NULL,
    message VARCHAR(1000),
    image_file_url VARCHAR(1000),
    image_file_name VARCHAR(50),
    image_file_type VARCHAR(10),
    image_file_size BIGINT,
    PRIMARY KEY (post_id),
    FOREIGN KEY (phone_number)
        REFERENCES cs551_team_project.user (phone_number)
        ON DELETE CASCADE
);

insert into cs551_team_project.post(phone_number, message, image_file_url, image_file_name, image_file_type, image_file_size)
values ("6", "test message", null, null, null, null);

select * from cs551_team_project.user;
select * from cs551_team_project.step_record;
select * from cs551_team_project.post;

delete from cs551_team_project.user where phone_number ="2";


INSERT INTO CS551_TEAM_PROJECT.USER (user_NAME, PHONE_NUMBER)VALUE("arvin", "5");
INSERT INTO cs551_team_project.step_record (step, record_start_date, record_end_date, phone_number)
	VALUES(200, '2015-07-15 19:18:00', '2015-07-15 19:18:00', "2");
SELECT * FROM CS551_TEAM_PROJECT.USER;
SELECT * FROM cs551_team_project.step_record;
SELECT * FROM userdb.user WHERE userid = 2;
DELETE FROM userdb.user 
WHERE
    userid = 7;
    
UPDATE USERDB.USER 
SET 
    NAME = 'arvin',
    AGE = 20
WHERE
    USERID = 1;
    
COMMIT;
SELECT LAST_INSERT_ID();
SHOW DATABASES;
SHOW TABLES FROM userdb;

SELECT * FROM userdb.stepdata;
SET time_zone='-07:00';
INSERT INTO userdb.stepdata(step, record_start_date, userid) VALUES(200, '2015-07-15 19:18:00', 5);
SET foreign_key_checks = 1;
SELECT step FROM userdb.stepdata 
WHERE userid = 5 AND record_end_date BETWEEN '2015-07-15 19:30:00' AND '2015-07-15 20:41:24';

INSERT INTO userDB.stepdata(step,record_start_date,userID) VALUES (200,'2015-07-15 20:40:00',5);


SELECT * FROM studentdb.scores;