package com.portfolio.TODO.statistic;


import com.portfolio.TODO.repository.DeletedTaskRepository;
import com.portfolio.TODO.repository.FinishedTaskRepository;
import com.portfolio.TODO.repository.TaskRepository;
import com.portfolio.TODO.repository.UserRepository;
import com.portfolio.TODO.service.FinishedTaskServiceImpl;
import com.portfolio.TODO.service.TaskServiceImpl;
import com.portfolio.TODO.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class Statistics {

    @Autowired
    UserRepository userRepo;

    @Autowired
    TaskRepository taskRepo;

    @Autowired
    FinishedTaskRepository finishedTaskRepo;

    @Autowired
    DeletedTaskRepository deletedTaskRepo;

    public Long getAllTasks(Long userId){
        Long totalTasksCount=taskRepo.findByUserId(userId).stream().count();
        Long totalDeletedTasksCount=deletedTaskRepo.findByUserId(userId).stream().count();
        Long totalCompletedTasksCount=finishedTaskRepo.findByUserId(userId).stream().count();
        Long totalCount=totalTasksCount+totalDeletedTasksCount+totalCompletedTasksCount;
        return totalCount;
    }

    public double getAllDoneTasks(Long userId){
        Long totalTasksCount=getAllTasks(userId);
        Long totalCompletedTasksCount=finishedTaskRepo.findByUserId(userId).stream().count();
        double totalFinishedCountPercentage=  ((double)totalCompletedTasksCount/totalTasksCount*100);
        return round(totalFinishedCountPercentage,1);
    }

    public double getAvgSpentTime(Long userId){
        Long totalCompletedTasksCount=finishedTaskRepo.findByUserId(userId).stream().count();
        long totalTime=finishedTaskRepo.findAvgSpentTimeByUser(userId);
        double avgTime=(double) totalTime/(double)totalCompletedTasksCount;
        return avgTime;
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
