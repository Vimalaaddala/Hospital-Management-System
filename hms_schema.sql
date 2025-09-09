-- SQL schema for hmsdb
CREATE DATABASE IF NOT EXISTS hmsdb;
USE hmsdb;

CREATE TABLE IF NOT EXISTS patients (
  id INT PRIMARY KEY,
  name VARCHAR(100),
  age INT,
  gender VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS doctors (
  id INT PRIMARY KEY,
  name VARCHAR(100),
  specialization VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS appointments (
  id INT PRIMARY KEY,
  patient_id INT,
  doctor_id INT,
  appointment_date DATE,
  FOREIGN KEY (patient_id) REFERENCES patients(id),
  FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);
