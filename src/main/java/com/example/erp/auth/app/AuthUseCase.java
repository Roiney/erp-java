package com.example.erp.auth.app;

import com.example.erp.auth.app.command.SignInCommand;
import com.example.erp.auth.app.dto.SignInResult;

public interface AuthUseCase {
    SignInResult signIn(SignInCommand cmd);
}
