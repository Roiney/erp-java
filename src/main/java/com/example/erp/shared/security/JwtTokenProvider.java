package com.example.erp.shared.security;

import com.example.erp.user.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final byte[] secret;
    private final long accessExp;
    private final long refreshExp;

    public JwtTokenProvider(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.access-exp-seconds}") long accessExp,
            @Value("${app.jwt.refresh-exp-seconds}") long refreshExp) {
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
        this.accessExp = accessExp;
        this.refreshExp = refreshExp;
    }

    public String generateAccessToken(User user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("name", user.getName())
                .claim("role", user.getRole().name())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(accessExp)))
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();
    }

    public String generateRefreshToken(User user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(refreshExp)))
                .signWith(Keys.hmacShaKeyFor(secret))
                .compact();
    }
}
