package com.spring_app.task_maker.controllers;

import com.spring_app.task_maker.models.task.Task;
import com.spring_app.task_maker.models.task.TaskDTO;
import com.spring_app.task_maker.services.TaskDBService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TaskController {

    private final TaskDBService db;

    public TaskController(TaskDBService db) {
        this.db = db;
    }

    @GetMapping("/tasks")
    public List<TaskDTO> getMappedTaskList() {



        return db.getTasksDTOList();
    }
    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDTO> getMappedTaskById(@PathVariable Integer id) throws ResponseStatusException  {
        TaskDTO task = db.getTaskById(id);
        return ResponseEntity.ok(task); 
    }
    

    @GetMapping("/tasks/title/{title}")
    public List<TaskDTO> getMappedTaskByTitle(@PathVariable String title) {
        return db.getTaskByTitle(title);
    }

    @PostMapping("/tasks/create")
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task savedTask = db.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }
}
