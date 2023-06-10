package com.portfolio.TODO.repository;

import com.portfolio.TODO.model.FinishedTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinishedTaskRepository extends JpaRepository<FinishedTask,Long> {
}

