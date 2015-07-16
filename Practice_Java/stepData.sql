create database userdb;
create table if not exists userdb.user(
	
    NAME VARCHAR(255) not null, 
    AGE INT not null, 
    HEIGHT DOUBLE not null,
    WEIGHT DOUBLE not null,
    USERID bigint NOT NULL AUTO_INCREMENT, 
    PRIMARY KEY(USERID)
);
INSERT INTO USERDB.USER (NAME, AGE, HEIGHT, WEIGHT)VALUE("arvin", 15, 20.5, 15.5);
SELECT * FROM USERDB.USER;

delete from userdb.user where userid=7;
UPDATE USERDB.USER SET NAME="arvin", AGE = 20 WHERE USERID=1;
commit;
SELECT lAST_INSERT_ID();
show databases;
show tables from userdb;
create table if not exists userdb.stepdata(
	step int not null,
    recordstartdate timestamp not null,
	recordenddate timestamp default current_timestamp,
	id bigint not null auto_increment,
    userid bigint,
    primary key(id),
    foreign key(userid) references userdb.user(userid)
    ON delete CASCADE
);
select * from userdb.stepdata;
set time_zone='+00:00';
insert into userdb.stepdata(step, recordstartdate, userid) values(10, '2015-07-15 19:18:00', 7);
