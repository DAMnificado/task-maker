package com.spring_app.task_maker.models.task;

import java.time.LocalDate;

public record TaskDTO(String title, String description, Priority priority, LocalDate dueDate)

 {
 
}
