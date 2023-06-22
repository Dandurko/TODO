package com.portfolio.TODO.service;

import com.portfolio.TODO.model.DeletedTask;


import java.util.List;


public interface DeletedTaskService {
    public void createDeletedTask(DeletedTask task);
    List<DeletedTask> getAllDeletedTask(Long userId);
}
