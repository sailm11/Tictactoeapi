package com.assessment.tictactoe.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.tictactoe.model.Game;
import com.assessment.tictactoe.model.GameMove;
import com.assessment.tictactoe.model.User;
import com.assessment.tictactoe.model.enums.GameStatus;
import com.assessment.tictactoe.repository.GameMoveRepository;
import com.assessment.tictactoe.repository.GameRepository;
import com.assessment.tictactoe.repository.UserRepository;

@Service
public class TicTacToeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameMoveRepository gameMoveRepository;

    public Game startGame(int player1Id, int player2Id) {
        Optional<User> player1 = userRepository.findById(player1Id);
        Optional<User> player2 = userRepository.findById(player2Id);

        if (player1.isPresent() && player2.isPresent()) {
            Game game = new Game();
            game.setPlayer1(player1.get());
            game.setPlayer2(player2.get());
            game.setStatus(GameStatus.IN_PROGRESS);
            return gameRepository.save(game);
        }

        throw new IllegalArgumentException("Invalid players for the game.");
    }

    public GameMove makeMove(int gameId, int playerId, int position) {
        Optional<Game> gameOpt = gameRepository.findById(gameId);
        Optional<User> playerOpt = userRepository.findById(playerId);

        if (gameOpt.isPresent() && playerOpt.isPresent()) {
            Game game = gameOpt.get();

            if (game.getStatus() != GameStatus.IN_PROGRESS) {
                throw new IllegalStateException("Game is not in progress.");
            }

            GameMove move = new GameMove();
            move.setGame(game);
            move.setPlayer(playerOpt.get());
            move.setPosition(position);
            move.setTimestamp(new Date(System.currentTimeMillis()).toString());
            return gameMoveRepository.save(move);
        }

        throw new IllegalArgumentException("Invalid game or player.");
    }

    public Game finishGame(int gameId, String result) {
        Optional<Game> gameOpt = gameRepository.findById(gameId);

        if (gameOpt.isPresent()) {
            Game game = gameOpt.get();
            game.setStatus(GameStatus.COMPLETED);
            game.setResult(result);
            return gameRepository.save(game);
        }

        throw new IllegalArgumentException("Game not found.");
    }
}
