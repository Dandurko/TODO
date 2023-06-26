package com.portfolio.TODO.service;

import com.portfolio.TODO.model.FinishedTask;
import com.portfolio.TODO.model.Task;

import java.util.List;

public interface FinishedTaskService {
    void createFinishedTask(FinishedTask task);
    List<FinishedTask> getAllFinishedTask(Long userId);
}
