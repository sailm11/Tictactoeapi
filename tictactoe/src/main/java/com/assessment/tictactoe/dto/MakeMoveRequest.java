package com.assessment.tictactoe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakeMoveRequest {
    private int playerId;
    private int position;
}
