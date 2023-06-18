package com.portfolio.TODO.service;

import com.portfolio.TODO.model.FinishedTask;
import com.portfolio.TODO.model.Task;
import com.portfolio.TODO.repository.FinishedTaskRepository;
import com.portfolio.TODO.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskRepository taskRepo;

    @Override
    public List<Task> getAllTask(Long userId) {
        return taskRepo.findByUserId(userId);
    }

    @Override
    public void create(Task task) {
        taskRepo.save(task);
    }

    @Override
    public Optional<Task> getDetailedTask(Long taskID) {
        return taskRepo.findById(taskID);
    }

    @Override
    public void delete(Long taskId) {
        taskRepo.deleteById(taskId);
    }

    @Override
    public void finish(Long taskId) {
        taskRepo.deleteById(taskId);
    }
}
