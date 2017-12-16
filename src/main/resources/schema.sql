
-- Spring boot enables the dataSource initializer by default and loads SQL scripts (schema.sql and data.sql) 
-- from the root of the classpath.

-- User
--CREATE USER IF NOT EXISTS rabbit IDENTIFIED BY 'hole';
--GRANT ALL ON rabbithole.* TO rabbit;

-- Schema
--CREATE SCHEMA IF NOT EXISTS rabbithole DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

-- Table
DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	email VARCHAR(100) UNIQUE NOT NULL,
	created_date DATE NOT NULL,
	PRIMARY KEY (id, email)
);
