package com.example.taskmanagement;

import java.time.LocalDate;

public class Task implements Comparable<Task>{
    private static int count =0;
    int taskID;
    String taskName;
    int priority;
    LocalDate deadLine;
    String taskDiscription;


    public Task(String taskName, int priority, LocalDate deadLine ,String taskDiscription) {
        taskID = count;
        count++;
        this.taskName = taskName;
        this.priority = priority;
        this.deadLine = deadLine;
        if(taskDiscription.length()>50)
            this.taskDiscription= taskDiscription.substring(0,50);
        else
            this.taskDiscription= taskDiscription;
    }

    @Override
    public int compareTo(Task o) {
        return deadLine.compareTo(o.deadLine);
    }

    @Override
    public String toString() {
        return taskName +"|| deadLine(" + deadLine+")";
    }

    static int getCount(){
        return count;
    }

}

