package com.jphilips.library.auth.controller;

import com.jphilips.library.auth.dto.LoginRequestDto;
import com.jphilips.library.auth.dto.TokenResponseDto;
import com.jphilips.library.auth.dto.UserRequestDto;
import com.jphilips.library.auth.dto.UserResponseDto;
import com.jphilips.library.auth.service.auth.AuthFacadeService;
import com.jphilips.shared.dto.AuthDetailsDto;
import com.jphilips.shared.validator.groups.OnCreate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    // Facade service to handle all auth-related commands
    private final AuthFacadeService authFacadeService;

    // Endpoint to authenticate a user and generate a JWT token.
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(
            @Valid
            @RequestBody LoginRequestDto loginRequestDTO) {

        return ResponseEntity.ok(authFacadeService.authenticate(loginRequestDTO));
    }

    //Endpoint to register a new user.
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
            @Validated(OnCreate.class)
            @RequestBody UserRequestDto userRequestDTO) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authFacadeService.register(userRequestDTO));
    }

    // Endpoint to validate the JWT token passed in the Authorization header.
    @GetMapping("/validate")
    public ResponseEntity<AuthDetailsDto> validateToken(
            @RequestHeader(value = "Authorization", required = false) String token) {

        return ResponseEntity.ok(authFacadeService.validateToken(token));
    }
}

