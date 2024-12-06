package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameResultRepository gameResultRepository;

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player) {
        return playerRepository.save(player);
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable Long id) {
        try {
            playerRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete player");
        }
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player updatedPlayer) {
        System.out.println("Received isPremium: " + updatedPlayer.isPremium());
        return playerRepository.findById(id)
                .map(player -> {
                    player.setName(updatedPlayer.getName());
                    player.setRegion(updatedPlayer.getRegion());
                    player.setTotalScore(updatedPlayer.getTotalScore());
                    player.setPremium(updatedPlayer.isPremium());
                    playerRepository.save(player);
                    return ResponseEntity.ok(player);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @PostMapping("/players/{id}/games")
    public ResponseEntity<GameResult> addGameToPlayer(@PathVariable Long id, @RequestBody GameResult gameResult) {
        return playerRepository.findById(id)
                .map(player -> {
                    gameResult.setPlayer(player);
                    gameResultRepository.save(gameResult);
                    int newScore = player.getTotalScore() + gameResult.getScore();
                    player.setTotalScore(Math.max(newScore, 0));
                    playerRepository.save(player);
    
                    return ResponseEntity.ok(gameResult);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return playerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
