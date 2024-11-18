package com.example;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
@NoArgsConstructor
public class GameResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score;
    private String gameType;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    @JsonBackReference
    private Player player;

    private LocalDateTime date = LocalDateTime.now();
}