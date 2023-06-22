package com.portfolio.TODO.service;

import com.portfolio.TODO.model.DeletedTask;
import com.portfolio.TODO.repository.DeletedTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeletedTaskServiceImpl implements DeletedTaskService{
    @Autowired
    DeletedTaskRepository repository;
    @Override
    public void createDeletedTask(DeletedTask task) {
        repository.save(task);

    }

    @Override
    public List<DeletedTask> getAllDeletedTask(Long userId) {
        return repository.findByUserId(userId);
    }
}
