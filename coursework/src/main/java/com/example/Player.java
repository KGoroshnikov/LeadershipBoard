package com.example;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int totalScore;
    private String region;
    private int isPremium;
    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE)
    @JsonIgnore // Игнорируем поле при сериализации
    private List<GameResult> gameResults;

    public boolean isPremium() {
        return (isPremium == 1) ? true : false;
    }

    public void setPremium(boolean isPremium) {
        this.isPremium = isPremium ? 1 : 0;
    }
}