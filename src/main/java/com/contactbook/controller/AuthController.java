package com.contactbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/contactbook")
public class AuthController {
    private final Map<String, String> users = new HashMap<>();

    @GetMapping("")
    public String landing() {
        return "landing";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        String storedPassword = users.get(username);
        if (storedPassword != null && storedPassword.equals(password)) {
            // For demo, just show a success message
            model.addAttribute("message", "Login successful!");
            return "landing";
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        if (users.containsKey(username)) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        users.put(username, password);
        model.addAttribute("message", "Account created! Please login.");
        return "login";
    }
}
