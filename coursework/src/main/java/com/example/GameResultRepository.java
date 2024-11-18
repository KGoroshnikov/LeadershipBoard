package com.example;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameResultRepository extends JpaRepository<GameResult, Long> {
    List<GameResult> findByPlayerId(Long playerId);

    @Query("SELECT g.date AS date, g.score AS score FROM GameResult g WHERE g.player.id = :playerId ORDER BY g.date ASC")
    List<Map<String, Object>> findScoreProgressByPlayerId(@Param("playerId") Long playerId);

}

