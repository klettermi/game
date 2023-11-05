package com.example.game.domain.hangman.repository;

import com.example.game.domain.hangman.entity.Hangman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HangmanRepository extends JpaRepository<Hangman, Long> {
}
