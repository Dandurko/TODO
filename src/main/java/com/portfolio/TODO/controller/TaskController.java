package com.portfolio.TODO.controller;

import com.portfolio.TODO.model.DeletedTask;
import com.portfolio.TODO.model.FinishedTask;
import com.portfolio.TODO.model.Task;
import com.portfolio.TODO.model.User;
import com.portfolio.TODO.service.DeletedTaskServiceImpl;
import com.portfolio.TODO.service.FinishedTaskServiceImpl;
import com.portfolio.TODO.service.TaskServiceImpl;
import com.portfolio.TODO.service.UserService;
import com.portfolio.TODO.statistic.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/page")
public class TaskController {

    @Autowired
    DeletedTaskServiceImpl deletedTaskService;
    @Autowired
    TaskServiceImpl taskService;

    @Autowired
    UserService userService;

    @Autowired
    Statistics stat;

    @Autowired
    FinishedTaskServiceImpl finishedTaskService;
    @GetMapping("/allTasks/{userId}")
    public String getPage(@PathVariable("userId") Long userId, Model model){
        List tasks = taskService.getAllTask(userId);
        Task task=new Task();
        model.addAttribute("tasks",tasks);
        model.addAttribute("task",task);
        model.addAttribute("userId",userId);
        stat.getAllTasks(userId);
        return "index.html";
    }

    @PostMapping("/saveTask/{userId}")
    public String createTask(@PathVariable("userId") Long userId,@ModelAttribute("task") Task task) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        task.setCreatedDate(currentTimestamp);
        Optional<User> user=userService.getUserById(userId);
        task.setUser(user.orElse(null));
        taskService.create(task);
        return "redirect:/page/allTasks/"+userId;

    }


    @GetMapping("/deleteTask/{taskId}/{userId}")
    public String deleteTask(@PathVariable("taskId") Long taskId,@PathVariable("userId") Long userId) {
        Optional<Task> existingTask = taskService.getDetailedTask(taskId);
        if (existingTask.isPresent()) {
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            Task task = existingTask.get();
            DeletedTask deletedTask = new DeletedTask();
            deletedTask.setName(task.getName());
            deletedTask.setDeletedDate(currentTimestamp);
            deletedTask.setCreatedDate(task.getCreatedDate());
            Optional<User> user=userService.getUserById(userId);
            deletedTask.setUser(user.orElse(null));
            deletedTaskService.createDeletedTask(deletedTask);
            taskService.delete(taskId);
        }
        return "redirect:/page/allTasks/"+userId;
    }


    @GetMapping("/finishTask/{taskId}/{userId}")
    public String finishTask(@PathVariable("taskId") Long taskId,@PathVariable("userId") Long userId) {
        Optional<Task> existingTask = taskService.getDetailedTask(taskId);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            FinishedTask finished_task = new FinishedTask();
            finished_task.setName(task.getName());
            finished_task.setCreatedDate(task.getCreatedDate());
            finished_task.setFinishedDate(new Timestamp(System.currentTimeMillis()));
            long timeDifferenceMillis = finished_task.getFinishedDate().getTime() - finished_task.getCreatedDate().getTime();
            long timeDifferenceSeconds = timeDifferenceMillis / 1000;
            finished_task.setTimeDifferenceSeconds(timeDifferenceSeconds);
            Optional<User> user=userService.getUserById(userId);
            finished_task.setUser(user.orElse(null));
            finishedTaskService.createFinishedTask(finished_task);
            taskService.finish(taskId);
        }
        return "redirect:/page/allTasks/"+userId;
    }

    @GetMapping("/finishedTasks/{userId}")
    public String getFinishedTasks(@PathVariable("userId") Long userId, Model model) {
        List finishedTasks = finishedTaskService.getAllFinishedTask(userId);
        model.addAttribute("finishedTasks",finishedTasks);
        return "finished-tasks.html";
    }

}