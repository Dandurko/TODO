package com.portfolio.TODO.repository;

import com.portfolio.TODO.model.DeletedTask;
import com.portfolio.TODO.model.FinishedTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeletedTaskRepository extends JpaRepository<DeletedTask,Long> {
    List<DeletedTask> findByUserId(Long userId);
}
