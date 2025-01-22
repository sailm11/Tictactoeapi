package com.assessment.tictactoe.model;

import java.util.List;

import com.assessment.tictactoe.model.enums.GameStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User player1;

    @ManyToOne
    private User player2;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GameMove> moves;

    @Column
    private String result;

}