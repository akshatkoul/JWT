package com.wings.may24.controller;

import com.wings.may24.model.request.UserDetailsModel;
import com.wings.may24.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/login")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody UserDetailsModel request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserId(), request.getPassword()));
        String token = jwtService.generateToken(request.getUserId());
        return ResponseEntity.ok(token);
    }
}
