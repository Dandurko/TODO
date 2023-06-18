package com.portfolio.TODO.controller;

import com.portfolio.TODO.model.FinishedTask;
import com.portfolio.TODO.model.Task;
import com.portfolio.TODO.service.FinishedTaskServiceImpl;
import com.portfolio.TODO.service.TaskServiceImpl;
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
    TaskServiceImpl taskService;

    @Autowired
    FinishedTaskServiceImpl finishedTaskService;
    @GetMapping("/allTasks/{userId}")
    public String getPage(@PathVariable("userId") Long userId, Model model){
        List tasks = taskService.getAllTask(userId);
        Task task=new Task();
        model.addAttribute("tasks",tasks);
        model.addAttribute("task",task);
        return "index.html";
    }

    @PostMapping("/saveTask")
    public String createTask(@ModelAttribute("task") Task task) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        task.setCreatedDate(currentTimestamp);
        taskService.create(task);
        return "redirect:/page/allTasks";

    }




    @GetMapping("/taskDetail/{taskId}")
    public String getTaskDetail(@PathVariable("taskId") Long taskId, Model model) {
        Optional<Task> task = taskService.getDetailedTask(taskId);
        model.addAttribute("task", task.orElse(null)); // Použite .orElse(null), ak Optional je prázdny
        return "task-detail"; // Nemusíte uvádzať príponu .html
    }


    @PostMapping("/deleteTask/{taskId}")
    public String deleteTask(@PathVariable("taskId") Long taskId) {
        Optional<Task> existingTask = taskService.getDetailedTask(taskId);
        if (existingTask.isPresent()) {
            taskService.delete(taskId);
        }
        return "redirect:/page/allTasks";
    }


    @GetMapping("/finishTask/{taskId}")
    public String finishTask(@PathVariable("taskId") Long taskId) {
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
            finishedTaskService.createFinishedTask(finished_task);
            taskService.finish(taskId);
        }
        return "redirect:/page/allTasks";
    }

    @GetMapping("/finishedTasks")
    public String getFinishedTasks(Model model) {
            List finishedTasks = finishedTaskService.getAllFinishedTask();
            model.addAttribute("finishedTasks",finishedTasks);

            return "finished-tasks.html";
        }

}
