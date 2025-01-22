package com.assessment.tictactoe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.tictactoe.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String name);

    public Optional<User> getById(int id);

    public User findByEmail(String email);
    // User loadbyUsername(String name);
}
