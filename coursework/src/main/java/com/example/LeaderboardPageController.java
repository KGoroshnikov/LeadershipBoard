package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaderboardPageController {

    @GetMapping("/leaderboard-page")
    public String showLeaderboardPage() {
        return "leaderboard"; // Возвращаем страницу leaderboard.html
    }
}
