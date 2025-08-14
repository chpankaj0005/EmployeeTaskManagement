CREATE DATABASE IF NOT EXISTS ems;
USE ems;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50),
    role VARCHAR(20)
);

CREATE TABLE employees (
    emp_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    email VARCHAR(50)
);

CREATE TABLE tasks (
    task_id INT PRIMARY KEY AUTO_INCREMENT,
    emp_id INT,
    task_desc VARCHAR(255),
    status VARCHAR(20),
    FOREIGN KEY (emp_id) REFERENCES employees(emp_id)
);

INSERT INTO users (username, password, role) VALUES 
('admin', 'admin123', 'ADMIN'),
('employee1', 'emp123', 'EMPLOYEE');
