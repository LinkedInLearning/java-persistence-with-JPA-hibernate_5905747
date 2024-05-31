-- Create a database
DROP DATABASE IF EXISTS artclass;
CREATE DATABASE IF NOT EXISTS artclass;
-- Use the database
USE artclass;
-- Crate tables in the database
CREATE TABLE IF NOT EXISTS class(
  class_id INT AUTO_INCREMENT PRIMARY KEY,
  class_name VARCHAR(255),
  day_of_week VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS teacher(
  teacher_id INT AUTO_INCREMENT PRIMARY KEY,
  teacher_name VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS student(
  student_id INT AUTO_INCREMENT PRIMARY KEY,
  student_name VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS student_class(
  student_id INT,
  class_id INT,
  FOREIGN KEY (student_id) REFERENCES student(student_id),
  FOREIGN KEY (class_id) REFERENCES class(class_id)
);
ALTER TABLE IF EXISTS class
ADD COLUMN teacher_id INT;
ALTER TABLE class
ADD CONSTRAINT fk_teacher_id FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id);
ALTER TABLE IF EXISTS teacher
ADD COLUMN class_id INT;
ALTER TABLE teacher
ADD CONSTRAINT fk_class_id FOREIGN KEY (class_id) REFERENCES class(class_id);