package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final CustomUserDetailsService userDetailsService;

    public AuthController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@RequestParam String username, @RequestParam String password, @RequestParam String confirmPassword, Model model) {
        try {
            if (!password.equals(confirmPassword)){
                model.addAttribute("error", "Registration failed");
                return "redirect:/register?error";
            }
            userDetailsService.registerUser(username, password);
            return "redirect:/login?success";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed");
            return "redirect:/register?error";
        }
    }
}
