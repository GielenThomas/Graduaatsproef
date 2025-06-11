package com.dnd.charactergenerator.controller;

import com.dnd.api.LoginApi;
import com.dnd.api.RegisterApi;
import com.dnd.charactergenerator.service.AuthService;
import com.dnd.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
@Controller
public class AuthController implements LoginApi, RegisterApi {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return LoginApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Object> registerPost(LoginRequest request) {
        try {
            authService.registerUser(request);
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<String> loginPost(LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
