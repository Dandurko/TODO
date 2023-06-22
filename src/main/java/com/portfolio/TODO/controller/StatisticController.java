package com.portfolio.TODO.controller;

import com.portfolio.TODO.statistic.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class StatisticController {

    @Autowired
    Statistics statistics;

    @GetMapping("/statistics/{userId}")
    public String getStatistics(Model model, @PathVariable("userId") Long userId){

        Long totalCount= statistics.getAllTasks(userId);
        double totalFinishedCountPercentage=statistics.getAllDoneTasks(userId);
        double avgTime=statistics.getAvgSpentTime(userId);
        model.addAttribute("totalCount",totalCount);
        model.addAttribute("totalFinishedCountPercentage",totalFinishedCountPercentage);
        model.addAttribute("avgTime",avgTime);
        return "statistic";

    }
}
