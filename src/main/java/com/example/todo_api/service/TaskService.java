package com.example.todo_api.service;

import com.example.todo_api.model.Task;
import com.example.todo_api.model.TaskStatus;
import com.example.todo_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {

        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.TO_DO);
        }
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(TaskStatus status) {
        if (status != null) {
            return taskRepository.findByStatus(status);
        }
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Optional<Task> updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            return taskRepository.save(task);
        });
    }

    public boolean deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false; // Task not found
    }
}
