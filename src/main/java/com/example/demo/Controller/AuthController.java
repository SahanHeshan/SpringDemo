package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignupForm() {return "signup";}
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }


    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, Model model) {
        try {
            userService.saveUser(user);
            return "redirect:/login";
        }catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "redirect:/signup";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password, Model model) {
        try {
            User user = userService.getUserByName(name);
            if (user != null && user.getPassword().equals(password)) {
                return "redirect:/";
            }
            return "login";
        }catch (Exception e){
            model.addAttribute("error", "Error: " + e.getMessage());
            return "redirect:/login";
        }
    }
}
