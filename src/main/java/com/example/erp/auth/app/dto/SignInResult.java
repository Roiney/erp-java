package com.example.erp.auth.app.dto;

public record SignInResult(String accessToken, String refreshToken, String userName) {}
