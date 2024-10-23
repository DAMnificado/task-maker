package com.spring_app.task_maker.controllers;

import com.spring_app.task_maker.models.task.Task;
import com.spring_app.task_maker.models.task.TaskDTO;
import com.spring_app.task_maker.services.TaskDBService;

import com.spring_app.task_maker.utils.messages.TaskMessage;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class TaskController {

    private final TaskDBService db;

    public TaskController(TaskDBService db) {
        this.db = db;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getMappedTaskList() {



       
        
        return ResponseEntity.ok(db.getTasksDTOList()); 



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
    public ResponseEntity<TaskMessage> createTask(@Valid @RequestBody Task task) {
        TaskMessage message = db.createTask(task);

        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @DeleteMapping("/tasks/delete/{id}")
    public ResponseEntity<String> deleteTaskbyId(@PathVariable Integer id) throws ResponseStatusException {

        db.deleteTask(id);
        // return ResponseEntity.noContent().build();
        return ResponseEntity.ok("Producto eliminado con éxito");

    }

    @PutMapping("tasks/update/{id}")
    public  ResponseEntity<TaskMessage> updateTaskById(@PathVariable Integer id, @RequestBody Task task) throws ResponseStatusException {
        
    
        return ResponseEntity.ok(db.updateTask(id, task));
    }
}


