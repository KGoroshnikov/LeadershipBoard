package com.example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    // Получение топ-лидеров
    /*@Query("SELECT p FROM Player p ORDER BY p.totalScore DESC")
    List<Player> findTopPlayers(Pageable pageable);

    // Фильтрация по региону
    @Query("SELECT p FROM Player p WHERE p.region = :region ORDER BY p.totalScore DESC")
    List<Player> findByRegion(@Param("region") String region, Pageable pageable);

    // Фильтрация по премиум-статусу
    @Query("SELECT p FROM Player p WHERE p.isPremium = true ORDER BY p.totalScore DESC")
    List<Player> findPremiumPlayers(Pageable pageable);*/

    List<Player> findByRegion(String region, Pageable pageable);
    List<Player> findByIsPremium(Boolean isPremium, Pageable pageable);
    List<Player> findByRegionAndIsPremium(String region, Boolean isPremium, Pageable pageable);
}
