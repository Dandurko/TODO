package com.portfolio.TODO.service;

import com.portfolio.TODO.model.FinishedTask;
import com.portfolio.TODO.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    public List<Task> getAllTask();
    public void create(Task task);
    public void createFinishedTask(FinishedTask task);
    public Optional<Task> getDetailedTask(Long taskId);
    public void update(Task task);
    public void delete(Long taskId);
}
