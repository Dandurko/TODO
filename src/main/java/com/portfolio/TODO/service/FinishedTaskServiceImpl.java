package com.portfolio.TODO.service;

import com.portfolio.TODO.model.FinishedTask;
import com.portfolio.TODO.model.Task;
import com.portfolio.TODO.repository.FinishedTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinishedTaskServiceImpl  implements FinishedTaskService{
    @Autowired
    FinishedTaskRepository finishedTaskRepo;
    @Override
    public void createFinishedTask(FinishedTask task) {
        finishedTaskRepo.save(task);
    }

    @Override
    public List<FinishedTask> getAllFinishedTask(Long userId) {
        return finishedTaskRepo.findByUserId(userId);
    }
}
