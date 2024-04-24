package com.example.taskmanagement;

import java.util.Date;

public class Task implements Comparable<Task>{
    String taskName;
    int priority;
    Date deadLine;
    String taskDiscription;
    boolean finished;

    public Task(String taskName, int priority, Date deadLine ,String taskDiscription) {
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
}
