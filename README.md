# Task Management System
This repository contains the source code for a Task Management System built using Java, JavaFX and MySQL. Follow the steps below to set up and run the project on your local machine.

# HOW TO RUN

## 1. Download and install XAMPP for Windows from: 
https://www.apachefriends.org/download.html

## 2. Start the Services:
- Open XAMPP Control Panel.
- Click "Start" for both "Apache" and "MySQL" services.

## 3. Create a Database:

- Click "Admin" for "MySQL".
- In the phpMyAdmin interface, create a new database named "task_managment".

## 4. Import Database Schema:

- Go to the SQL tab in phpMyAdmin.
- Paste the following SQL code and click "Go":

CREATE TABLE `userr` (
  `Username` varchar(20) PRIMARY KEY,
  `Password` varchar(20) NOT NULL
);

CREATE TABLE `task` (
  `Task_ID` int(11) PRIMARY KEY,
  `T_Username` varchar(20) DEFAULT NULL,
  `Task_Name` varchar(100) NOT NULL,
  `Priority` int(11) NOT NULL,
  `Deadline` date NOT NULL DEFAULT current_timestamp(),
  `Description` varchar(100) NOT NULL,
  CONSTRAINT `T_Username_FK` FOREIGN KEY (`T_Username`) REFERENCES `userr` (`Username`) ON UPDATE CASCADE
);

CREATE TABLE `count` (
  `countt` int(11) DEFAULT 0
);

## 5. Download MySQL Connector Java:
- Download the MySQL Connector Java library (.jar file). You can find it on the official MySQL website.

## 6. Configure Project:
- Add the downloaded MySQL Connector Java .jar file to your project's classpath (refer to your IDE's documentation for specific instructions).

## 7. Run the application:
- Use a Java IDE (e.g., Eclipse, IntelliJ IDEA) to open and run the project.


