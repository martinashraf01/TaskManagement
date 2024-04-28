package com.example.taskmanagement;
import java.util.ArrayList;
import java.util.Collections;

public class User {
    private String userName ;
    private String password;
    ArrayList<Task> tasks ;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.tasks = new ArrayList<>();
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    void sortTasks(){
        Collections.sort(tasks);
    }
    //lazem f el hetta el hanhandle fiha ne3mel f allTasks 3ashan el admin yeshouf

    public void addTask(Task task){
        if (tasks.size()==0){
            tasks.add(task);
        }else {
            int i =0;
            for (Task t:tasks) {
                if(task.compareTo(t)<0){
                    i++;
                }else {
                    tasks.add(i,task);
                    return;
                }
            }
            tasks.add(task);
        }

    }
    public String toString(){
        return userName;
    }
}


