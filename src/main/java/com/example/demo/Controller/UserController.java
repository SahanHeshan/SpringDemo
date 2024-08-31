package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Home - List all users
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "home";
    }

    // Add marks to a user by name
    @PostMapping("/addMarks")
    public String addMarks(@RequestParam String name, @RequestParam Integer marks, Model model) {
        if (name == null || name.isEmpty() || marks == null || marks <= 0){
            model.addAttribute("error", "Invalid input:");
        }else {
            userService.addMarks(name, marks);
        }
        return "redirect:/";
    }
}
