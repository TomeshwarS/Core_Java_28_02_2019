
create database onlinejavasupport;
use onlinejavasupport;
create table questions(id bigint(10) auto_increment primary key,name varchar(40),marks varchar(40));
create table options(id bigint(10) auto_increment primary key,name varchar(40),question_id bigint(10));
create table answer(id bigint(10) auto_increment primary key ,question_id bigint(40),answer_id bigint(20));
create table users(id bigint(10) auto_increment primary key,name varchar(40),pass varchar(30),isActive boolean);
create table test(id bigint(10) auto_increment primary key,user_id bigint(10),question_anwser bigint(10),average bigint(10));