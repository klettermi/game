package com.example.game.domain.baseball.repository;

import com.example.game.domain.baseball.entity.BaseballGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseballRepository extends JpaRepository<BaseballGame, Long> {
}
