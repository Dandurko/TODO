package com.portfolio.TODO.repository;

import com.portfolio.TODO.model.FinishedTask;
import com.portfolio.TODO.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinishedTaskRepository extends JpaRepository<FinishedTask,Long> {
    List<FinishedTask> findByUserId(Long userId);
}

