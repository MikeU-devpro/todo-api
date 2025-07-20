package com.example.todo_api.repository;

import com.example.todo_api.model.Task;
import com.example.todo_api.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Marks this interface as a Spring Data JPA repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Custom query method to find tasks by status
    List<Task> findByStatus(TaskStatus status);
}

