package com.example.game.domain.baseball.repository;

import com.example.game.domain.baseball.entity.BaseballGame;
import com.example.game.domain.baseball.entity.BaseballUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseballUserRepository extends JpaRepository<BaseballUser, Long> {
    Optional<BaseballUser> findAllByBaseballGame(BaseballGame baseballGame);
}
