package com.example.erp.auth.web;

import com.example.erp.auth.app.AuthUseCase;
import com.example.erp.auth.app.command.SignInCommand;
import com.example.erp.auth.app.dto.SignInResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Tag(name = "Auth", description = "Autenticação e emissão de tokens")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthUseCase auth;
    public AuthController(AuthUseCase auth) { this.auth = auth; }

    @Operation(
            summary = "Login (sign-in)",
            description = "Autentica por email/senha e retorna tokens JWT",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Credenciais inválidas"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    @PostMapping("/signin")
    public ResponseEntity<SignInResult> signIn(@RequestBody @Valid SignInCommand cmd) {
        return ResponseEntity.ok(auth.signIn(cmd));
    }
}
