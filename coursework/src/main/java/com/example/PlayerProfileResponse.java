package com.example;

import java.util.List;

public class PlayerProfileResponse {
    private Player player;
    private List<GameResult> history;

    public PlayerProfileResponse(Player player, List<GameResult> history) {
        this.player = player;
        this.history = history;
    }

    public Player getPlayer() {
        return player;
    }

    public List<GameResult> getHistory() {
        return history;
    }
}
