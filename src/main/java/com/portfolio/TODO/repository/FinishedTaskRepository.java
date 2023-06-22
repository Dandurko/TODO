package com.portfolio.TODO.repository;

import com.portfolio.TODO.model.FinishedTask;
import com.portfolio.TODO.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinishedTaskRepository extends JpaRepository<FinishedTask,Long> {
    List<FinishedTask> findByUserId(Long userId);
    @Query("SELECT SUM(ft.timeDifferenceSeconds) FROM FinishedTask ft WHERE ft.user.id = :userId")
    Long findAvgSpentTimeByUser(@Param("userId") Long userId);
}

