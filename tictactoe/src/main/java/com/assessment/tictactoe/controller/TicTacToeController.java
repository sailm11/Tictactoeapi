package com.assessment.tictactoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.tictactoe.dto.MakeMoveRequest;
import com.assessment.tictactoe.dto.StartGameRequest;
import com.assessment.tictactoe.model.Game;
import com.assessment.tictactoe.model.GameMove;
import com.assessment.tictactoe.service.TicTacToeService;

@RestController
@RequestMapping("/api/")
public class TicTacToeController {

    @Autowired
    private TicTacToeService ticTacToeService;

    @PostMapping("/games/start")
    public Game startGame(@RequestBody StartGameRequest request) {
        return ticTacToeService.startGame(request.getPlayer1Id(), request.getPlayer2Id());
    }

    @PostMapping("/games/{gameId}/move")
    public GameMove makeMove(@PathVariable int gameId, @RequestBody MakeMoveRequest request) {
        return ticTacToeService.makeMove(gameId, request.getPlayerId(), request.getPosition());
    }

    @PostMapping("/games/{gameId}/finish")
    public Game finishGame(@PathVariable int gameId, @RequestParam String result) {
        return ticTacToeService.finishGame(gameId, result);
    }
}
