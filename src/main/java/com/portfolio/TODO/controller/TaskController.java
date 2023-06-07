package com.portfolio.TODO.controller;

import com.portfolio.TODO.model.Task;
import com.portfolio.TODO.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/page")
public class TaskController {

    @Autowired
    TaskServiceImpl service;

    @GetMapping("/allTasks")
    public String getPage(Model model){
        List tasks = service.getAllTask();
        Task task=new Task();
        model.addAttribute("tasks",tasks);
        model.addAttribute("task",task);
        return "index.html";
    }

    @PostMapping("/saveTask")
    public String createTask(@ModelAttribute("task") Task task) {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        task.setCreatedDate(currentTimestamp);
        task.setLastUpdate(currentTimestamp);
        service.create(task);
        return "redirect:/page/allTasks";

    }




    @GetMapping("/taskDetail/{taskId}")
    public String getTaskDetail(@PathVariable("taskId") Long taskId, Model model) {
        Optional<Task> task = service.getDetailedTask(taskId);
        model.addAttribute("task", task.orElse(null)); // Použite .orElse(null), ak Optional je prázdny
        return "task-detail"; // Nemusíte uvádzať príponu .html
    }


    @PostMapping("/taskDetail/{taskId}")
    public String editTaskDetail(@PathVariable("taskId") Long taskId, @ModelAttribute("task") Task updatedTask) {
        Optional<Task> existingTask = service.getDetailedTask(taskId);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            // Ponechajte createdDate nezmenené
            task.setName(updatedTask.getName()); // Prípadne aktualizujte ďalšie vlastnosti úlohy podľa potreby
            service.update(task);
        }
        return "redirect:/page/allTasks";
    }


    @GetMapping("/taskDelete/{taskId}")
    public String taskDelete(@PathVariable("taskId") Long taskId) {
        service.delete(taskId);
        return "redirect:/page/allTasks";
    }


}
