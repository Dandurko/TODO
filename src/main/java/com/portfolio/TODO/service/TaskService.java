package com.portfolio.TODO.service;

import com.portfolio.TODO.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
     List<Task> getAllTask(Long userId);
     void create(Task task);
     Optional<Task> getDetailedTask(Long taskId);
     void delete(Long taskId);
     void finish(Long taskId);
}
