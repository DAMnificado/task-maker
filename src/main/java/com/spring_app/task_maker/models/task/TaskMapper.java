package com.spring_app.task_maker.models.task;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    // Método para convertir Task a TaskDTO
    public TaskDTO toDTO(Task task) {
        if (task == null) {
            return null; // Manejo de caso nulo
        }
        return new TaskDTO(
            task.getTitle(),
            task.getDescription(),
            task.getPriority(),
            task.getDueDate()
        );
    }

    // Método para convertir TaskDTO a Task
    public Task toEntity(TaskDTO taskDTO) {
        if (taskDTO == null) {
            return null; // Manejo de caso nulo
        }
        Task task = new Task();
        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setPriority(taskDTO.priority());
        task.setDueDate(taskDTO.dueDate());
        return task;
    }


    
}
