package com.portfolio.TODO.controller;

import com.portfolio.TODO.model.User;
import com.portfolio.TODO.repository.UserRepository;
import com.portfolio.TODO.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;

@Controller
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;



    @GetMapping("/registration")
    public String getRegistration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
    @GetMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute User user){
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        user.setCreatedDate(currentTimestamp);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        service.createUser(user);
        return "redirect:/api/auth/login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("username") String username, @ModelAttribute("password") String password, RedirectAttributes redirectAttributes){
        User user=userRepository.findByUsername(username);
        if (user!=null && passwordEncoder.matches(password,user.getPassword())){
            Long userId = user.getId();
            redirectAttributes.addAttribute("userId", userId);
            return "redirect:/page/allTasks/{userId}";

        }
        else{
            return "redirect:/api/auth/login";
        }
    }
}
