package com.assessment.tictactoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.assessment.tictactoe.model.User;
import com.assessment.tictactoe.repository.UserRepository;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User getbyId(int id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public boolean userExist(String name) {
        if (userRepository.findByUsername(name) != null) {
            return true;
        }
        return false;
    }

    // public User loadByUserName(String username) {
    // return userRepository.findByName(username);
    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user;
        try {
            user = (UserDetails) userRepository.findByUsername(username);
            // if(userdetail==null){

            // }

        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found" + username);
        }
        // return userdetail.map(UserInfoDetails::new)
        // .orElseThrow(() -> new UsernameNotFoundException("User Not Found : " +
        // username));
        // throw new UnsupportedOperationException("Unimplemented method
        // 'loadUserByUsername'");
        return user;
    }
}
