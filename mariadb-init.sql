-- Create a database
DROP DATABASE IF EXISTS library;
CREATE DATABASE IF NOT EXISTS library;
-- Use the database
USE library;
-- Crate tables in the database
CREATE TABLE IF NOT EXISTS book(
  book_id INT AUTO_INCREMENT PRIMARY KEY,
  book_name VARCHAR(255),
  isbn VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS author(
  author_id INT AUTO_INCREMENT PRIMARY KEY,
  author_name VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS book_type(
  type_code VARCHAR(255),
  type_subcode VARCHAR(255),
  type_name VARCHAR(255),
  PRIMARY KEY(type_code, type_subcode)
);
CREATE TABLE IF NOT EXISTS item(
  item_code VARCHAR(255),
  item_number INT,
  item_name VARCHAR(255),
  PRIMARY KEY(item_code, item_number)
);