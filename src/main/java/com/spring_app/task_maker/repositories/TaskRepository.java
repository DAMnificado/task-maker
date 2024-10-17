package com.spring_app.task_maker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_app.task_maker.models.task.Task;
import java.util.List;


public interface TaskRepository extends JpaRepository<Task , Integer>{

List<Task> findByTitleContainingIgnoreCase(String title);



}
