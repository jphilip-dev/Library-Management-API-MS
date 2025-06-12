package com.jphilips.library.auth.service.auth;

import com.jphilips.library.auth.dto.LoginRequestDto;
import com.jphilips.library.auth.dto.TokenResponseDto;
import com.jphilips.library.auth.dto.UserRequestDto;
import com.jphilips.library.auth.dto.UserResponseDto;
import com.jphilips.library.auth.dto.cqrs.command.AuthenticateCommand;
import com.jphilips.library.auth.dto.cqrs.command.RegisterUserCommand;
import com.jphilips.library.auth.dto.cqrs.command.ValidateTokenCommand;
import com.jphilips.shared.dto.AuthDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthFacadeService {

    private final AuthenticateCommandService authenticateCommandService;
    private final RegisterUserCommandService registerUserCommandService;
    private final ValidateTokenCommandService validateTokenCommandService;

    public TokenResponseDto authenticate(LoginRequestDto loginRequestDto){

        var authenticateCommand = AuthenticateCommand.builder()
                .loginRequestDto(loginRequestDto)
                .build();

        return authenticateCommandService.execute(authenticateCommand);
    }

    public UserResponseDto register(UserRequestDto userRequestDto){

        var registerUserCommand = RegisterUserCommand. builder()
                .userRequestDto(userRequestDto)
                .build();

        return registerUserCommandService.execute(registerUserCommand);
    }

    public AuthDetailsDto validateToken(String token){

        var validateTokenCommand = ValidateTokenCommand.builder()
                .token(token)
                .build();

        return validateTokenCommandService.execute(validateTokenCommand);
    }
}
