package com.example.erp.auth.app;

import com.example.erp.auth.app.command.SignInCommand;
import com.example.erp.auth.app.dto.SignInResult;
import com.example.erp.shared.security.JwtTokenProvider;
import com.example.erp.user.domain.User;
import com.example.erp.user.infra.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements AuthUseCase {
    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwt;

    public AuthService(UserRepository users, PasswordEncoder passwordEncoder, JwtTokenProvider jwt) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.jwt = jwt;
    }

    @Transactional(readOnly = true)
    @Override
    public SignInResult signIn(@Valid SignInCommand cmd) {
        User user = users.findByEmail(cmd.email())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (!user.isActive()) {
            throw new IllegalArgumentException("User is inactive");
        }
        if (!passwordEncoder.matches(cmd.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return new SignInResult(
                jwt.generateAccessToken(user),
                jwt.generateRefreshToken(user),
                user.getName()
        );
    }
}
