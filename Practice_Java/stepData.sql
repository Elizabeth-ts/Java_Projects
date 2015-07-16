create database userdb;
CREATE TABLE IF NOT EXISTS userdb.user (
    USERID BIGINT NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL,
    AGE INT NOT NULL,
    HEIGHT DOUBLE NOT NULL,
    WEIGHT DOUBLE NOT NULL,
    PRIMARY KEY (USERID)
);
INSERT INTO USERDB.USER (NAME, AGE, HEIGHT, WEIGHT)VALUE("arvin", 15, 20.5, 15.5);
SELECT * FROM USERDB.USER;
select * from userdb.user where userid = 2;
DELETE FROM userdb.user 
WHERE
    userid = 7;
    
UPDATE USERDB.USER 
SET 
    NAME = 'arvin',
    AGE = 20
WHERE
    USERID = 1;
    
commit;
SELECT LAST_INSERT_ID();
show databases;
show tables from userdb;
CREATE TABLE IF NOT EXISTS userdb.stepdata (
    step INT NOT NULL,
    record_start_date TIMESTAMP NOT NULL,
    record_end_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id BIGINT NOT NULL AUTO_INCREMENT,
    userid BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (userid)
        REFERENCES userdb.user (userid)
        ON DELETE CASCADE
);
SELECT * FROM userdb.stepdata;
set time_zone='-07:00';
insert into userdb.stepdata(step, record_start_date, userid) values(200, '2015-07-15 19:18:00', 5);
set foreign_key_checks = 1;
select step from userdb.stepdata 
where userid = 5 and record_end_date between '2015-07-15 19:30:00' and '2015-07-15 20:41:24';

INSERT INTO userDB.stepdata(step,record_start_date,userID) VALUES (200,'2015-07-15 20:40:00',5);
