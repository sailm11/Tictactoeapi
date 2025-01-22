package com.assessment.tictactoe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.tictactoe.model.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
    public Optional<Game> findById(int id);
}
