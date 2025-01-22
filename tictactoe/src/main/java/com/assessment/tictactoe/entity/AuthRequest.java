package com.assessment.tictactoe.entity;

import lombok.Data;

@Data
public class AuthRequest {
    private String userName;
    private String passWord;
    private String email;
}
