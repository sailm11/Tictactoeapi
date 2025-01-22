package com.assessment.tictactoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.tictactoe.model.GameMove;

public interface GameMoveRepository extends JpaRepository<GameMove, Integer> {

}
