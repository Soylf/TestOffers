package com.example.demo.config.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("12345671231231231231321234153675789asdf".getBytes());

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 час
                .signWith(SECRET_KEY)
                .compact();
    }
}


