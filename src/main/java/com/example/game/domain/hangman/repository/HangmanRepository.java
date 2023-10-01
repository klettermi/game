package com.example.game.domain.hangman.repository;

import com.example.game.domain.hangman.entity.HangmanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HangmanRepository extends JpaRepository<HangmanHistory, Long> {
}
