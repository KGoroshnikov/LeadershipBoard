package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class GameResultController {

    @Autowired
    private GameResultRepository gameResultRepository;

    @PostMapping
    public GameResult addGameResult(@RequestBody GameResult gameResult) {
        return gameResultRepository.save(gameResult);
    }

    @GetMapping
    public List<GameResult> getAllResults() {
        return gameResultRepository.findAll();
    }

}
