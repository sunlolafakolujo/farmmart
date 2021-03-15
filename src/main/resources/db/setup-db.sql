drop user if exists 'farmmart'@'localhost';
Create user 'farmmart'@'localhost' identified by 'farmmart123';
grant all privileges on farmmartdb.* to 'farmmart'@'localhost';
flush privileges;



drop database if exists farmmartdb;
create database farmmartdb;