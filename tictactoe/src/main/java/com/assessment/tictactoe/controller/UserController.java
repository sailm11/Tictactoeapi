package com.assessment.tictactoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.tictactoe.model.User;
import com.assessment.tictactoe.service.UserService;
import com.assessment.tictactoe.utils.JwtUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private JwtUtils utils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/health")
    public String health() {
        return "Okay";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody com.assessment.tictactoe.model.User user) {
        String token = null;
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            if (authentication.isAuthenticated()) {
                token = utils.generateToken(user.getUsername());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("It seems you are not registered..");
        }
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            if (!service.userExist(user.getUsername())) {
                service.saveUser(user);
            }
        } catch (Exception e) {
            ResponseEntity.badRequest().body(e.toString());
        }

        return ResponseEntity.ok().body("User Created..");
    }
}
