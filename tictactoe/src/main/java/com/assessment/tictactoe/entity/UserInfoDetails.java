package com.assessment.tictactoe.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.assessment.tictactoe.model.User;

// @AllArgsConstructor
// @NoArgsConstructor
// @Data
public class UserInfoDetails implements UserDetails {
    private int id;
    private String username;
    private String password;
    private String email;
    // public List<GrantedAuthority> authorities;

    public UserInfoDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAuthorities'");
    }
}
