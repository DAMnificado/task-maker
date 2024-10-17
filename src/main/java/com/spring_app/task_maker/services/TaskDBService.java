package com.spring_app.task_maker.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.spring_app.task_maker.models.task.Task;
import com.spring_app.task_maker.models.task.TaskDTO;
import com.spring_app.task_maker.models.task.TaskMapper;
import com.spring_app.task_maker.repositories.TaskRepository;

@Service
public class TaskDBService {

    private TaskRepository rep;
    private TaskMapper mapper;

    public TaskDBService(TaskRepository rep, TaskMapper mapper) {
        this.rep = rep;
        this.mapper = mapper;
    }

    public List<TaskDTO> getTasksDTOList()  throws ResponseStatusException{
        List<Task> taskList = rep.findAll();
        return taskList.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
               
    }

    public TaskDTO getTaskById(Integer id) throws ResponseStatusException {
        Task task = rep.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el recurso con ID: " + id)); // Lanzar ResponseStatusException
        return mapper.toDTO(task);
    }

    public List<TaskDTO> getTaskByTitle(String title) {
        List<Task> tasks = rep.findByTitleContainingIgnoreCase(title);
        if (tasks.size() <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado ninguna tarea que contenga el titulo: " + title);
        }
        return tasks.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public Task createTask(Task task) {
        return rep.save(task);
    }
}
