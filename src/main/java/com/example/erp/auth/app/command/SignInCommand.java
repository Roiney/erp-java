package com.example.erp.auth.app.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignInCommand(@Email String email, @NotBlank String password) {}
