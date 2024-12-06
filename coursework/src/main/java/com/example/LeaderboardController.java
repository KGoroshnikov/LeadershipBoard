package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    @Autowired
    private PlayerRepository playerRepository;

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
            return playerRepository.findByIsPremium(isPremium ? 1 : 0, PageRequest.of(0, limit, sort));
        } else {
            return playerRepository.findAll(PageRequest.of(0, limit, sort)).getContent();
        }
    }

    @GetMapping("/statistics/regions")
    public Map<String, Long> getPlayersByRegion() {
        List<Object[]> results = playerRepository.countPlayersByRegion();
        Map<String, Long> regionStatistics = new HashMap<>();
        for (Object[] result : results) {
            regionStatistics.put((String) result[0], (Long) result[1]);
        }
        return regionStatistics;
    }

}
