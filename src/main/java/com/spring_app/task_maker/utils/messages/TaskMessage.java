package com.spring_app.task_maker.utils.messages;

import com.spring_app.task_maker.models.task.Task;

public class TaskMessage {

    
    public String message;
    public Task task;
    
    public TaskMessage(String message, Task task) {
        this.message = message;
        this.task = task;
    }
}

