package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaderboardPageController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/leaderboard-page")
    public String showLeaderboardPage(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("isAdmin", user.getRole().equals(User.Role.ADMIN));
        return "leaderboard";
    }
}
