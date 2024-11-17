package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    @Autowired
    private PlayerRepository playerRepository;

    // Получение топ-лидеров
    /*@GetMapping("/top")
    public List<Player> getTopPlayers(@RequestParam(defaultValue = "10") int limit) {
        return playerRepository.findTopPlayers(PageRequest.of(0, limit));
    }

    // Получение лидеров по региону
    @GetMapping("/region")
    public List<Player> getTopPlayersByRegion(@RequestParam String region, @RequestParam(defaultValue = "10") int limit) {
        return playerRepository.findByRegion(region, PageRequest.of(0, limit));
    }

    // Получение премиум-лидеров
    @GetMapping("/premium")
    public List<Player> getPremiumPlayers(@RequestParam(defaultValue = "10") int limit) {
        return playerRepository.findPremiumPlayers(PageRequest.of(0, limit));
    }*/

    // Получение списка игроков с фильтрами и сортировкой
    @GetMapping
    public List<Player> getFilteredLeaderboard(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) Boolean isPremium,
            @RequestParam(defaultValue = "totalScore") String sortBy,
            @RequestParam(defaultValue = "desc") String order,
            @RequestParam(defaultValue = "10") int limit
    ) {
        Sort.Direction direction = order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);

        if (region != null && isPremium != null) {
            return playerRepository.findByRegionAndIsPremium(region, isPremium, PageRequest.of(0, limit, sort));
        } else if (region != null) {
            return playerRepository.findByRegion(region, PageRequest.of(0, limit, sort));
        } else if (isPremium != null) {
            return playerRepository.findByIsPremium(isPremium, PageRequest.of(0, limit, sort));
        } else {
            return playerRepository.findAll(PageRequest.of(0, limit, sort)).getContent();
        }
    }
}
