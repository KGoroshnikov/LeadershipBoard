package com.example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByRegion(String region, Pageable pageable);
    List<Player> findByIsPremium(int isPremium, Pageable pageable);
    List<Player> findByRegionAndIsPremium(String region, Boolean isPremium, Pageable pageable);

    @Query("SELECT p.region AS region, COUNT(p) AS count FROM Player p GROUP BY p.region")
    List<Object[]> countPlayersByRegion();
}
