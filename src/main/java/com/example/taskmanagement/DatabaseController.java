package com.example.taskmanagement;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseController {
    //Start database connection
    public static Statement stmt;
    public static String command = new String();

    public static ResultSet rs;
    public static void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/task_managment","root","");
            stmt=connection.createStatement();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void retrieveOldData() throws SQLException {
        //Retrieve Users
        command = "SELECT * FROM userr;";
        rs = stmt.executeQuery(command);
        while (rs.next()) {
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            Controller.allUsers.add(new User(username, password));
        }



        //Retrieve tasks
        command = "SELECT * FROM task;";
        rs = stmt.executeQuery(command);
        while (rs.next()) {
            int taskID = rs.getInt("Task_ID");
            String taskName = rs.getString("Task_Name");
            int priority = rs.getInt("Priority");
            LocalDate deadline = rs.getDate("Deadline").toLocalDate();
            String taskDescription = rs.getString("Description");
            String u_username = rs.getString("T_Username");

            Task t = new Task(taskName, priority, deadline, taskDescription);
            Controller.allTasks.add(t);

            for (User user : Controller.allUsers) {
                if (user.toString().equals(u_username)) {
                    user.tasks.add(t);
                }
            }
        }


        //Retrieve static Count
        command = "SELECT * FROM count;";
        rs = stmt.executeQuery(command);
        while (rs.next()) {
            int countt = rs.getInt("countt");
            Task.setCount(countt);
        }
    }


    public static void addTaskDatabase(Task t,User user){
        //add to database
        Date sqlDate = Date.valueOf(t.getDeadLine());
        command = "INSERT INTO task VALUES (" + Task.getCount() + ",'" + user.getUserName() + "','"+ t.getTaskName() +"',"+t.getPriority()+",'"+sqlDate+"','"+t.getTaskDiscription() +"');";
        try {
            stmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e);
        }
        command = "UPDATE count SET countt = "+ Task.getCount() +";";
        try {
            stmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public static void addUserDatabase(User user){
        //add to database
        command = "INSERT INTO userr VALUES ('" + user.getUserName() + "','" + user.getPassword() + "');";
        try {
            stmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static void deleteTaskDatabase(Task t){
        command = "DELETE FROM task WHERE Task_ID = "+ t.getTaskID()+" ;";
        try {
            stmt.execute(command);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
