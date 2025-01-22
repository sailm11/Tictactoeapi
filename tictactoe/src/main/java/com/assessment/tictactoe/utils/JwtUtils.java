package com.assessment.tictactoe.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
    private final String secret_key = "xczft235!#$#@684pokmnj(*^hhgSLP)";

    private SecretKey getSigninKey() {
        return Keys.hmacShaKeyFor(secret_key.getBytes());
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigninKey()).build().parseSignedClaims(token).getPayload();

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    @SuppressWarnings("deprecation")
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().claims(claims).subject(subject)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256).compact();

    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String name = extractUsername(token);
        return (name.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
