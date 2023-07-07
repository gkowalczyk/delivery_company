package com.example.domains.controller;

import com.example.domains.config.JwUtil;
import com.example.domains.config.AuthenticationRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/auth")
//@RequiredArgsConstructor


public class AuthController {

    private final  AuthenticationManager authenticationManager;
    private final  UserDetailsService userDetailsService;
    private final  JwUtil jwUtil;

    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwUtil jwUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwUtil = jwUtil;
    }


    @PostMapping("/auth")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        final UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        if (user != null) {
            return ResponseEntity.ok(jwUtil.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some errors...");
    }
}
