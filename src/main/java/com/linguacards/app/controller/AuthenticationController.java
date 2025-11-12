package com.linguacards.app.controller;

import com.linguacards.app.dto.AuthRequest;
import com.linguacards.app.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final JWTUtils jwtUtils;

    private final AuthenticationManager authenticationManager;
//    temporary
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String authenticate(@RequestBody AuthRequest authRequest) {
        log.info("PasswordEncoder in AuthService: {}", passwordEncoder.getClass());
        var authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword());

        authenticationManager.authenticate(authentication);

        var token = jwtUtils.generateToken(authRequest.getUsername());
        return token;
    }
}
