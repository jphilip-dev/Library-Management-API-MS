package com.jphilips.library.auth.service.auth;

import com.jphilips.library.auth.dto.TokenResponseDto;
import com.jphilips.library.auth.dto.cqrs.command.AuthenticateCommand;
import com.jphilips.library.auth.exception.custom.UserInactiveException;
import com.jphilips.library.auth.exception.custom.UserPasswordMismatchException;
import com.jphilips.library.auth.service.helper.UserManager;
import com.jphilips.library.auth.util.JwtUtil;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateCommandService implements Command<AuthenticateCommand, TokenResponseDto> {

    private final UserManager userManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;


    @Override
    public TokenResponseDto execute(AuthenticateCommand command) {

        // Extract
        var loginRequestDto = command.loginRequestDto();

        // Validate email
        var user = userManager.validateUserByEmail(loginRequestDto.email());

        // Validate Password
        if(!passwordEncoder.matches(loginRequestDto.password(), user.getPassword())){
            throw new UserPasswordMismatchException(ErrorCode.AUTH_ERROR_PASSWORD_MISMATCH);
        }

        // Check Account Status
        if (!user.getIsActive()){
            throw new UserInactiveException(ErrorCode.AUTH_ERROR_USER_INACTIVE);
        }

        // Generate Token
        String token = jwtUtil.generateToken(user);

        // Return TokenResponseDto
        return new TokenResponseDto(token);
    }
}
