package com.jphilips.library.auth.service.auth;

import com.jphilips.library.auth.dto.cqrs.command.ValidateTokenCommand;
import com.jphilips.library.auth.exception.custom.MissingJwtException;
import com.jphilips.library.auth.util.JwtUtil;
import com.jphilips.shared.dto.AuthDetailsDto;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ValidateTokenCommandService implements Command<ValidateTokenCommand, AuthDetailsDto> {

    private final JwtUtil jwtUtil;

    @Override
    public AuthDetailsDto execute(ValidateTokenCommand command) {

        // Extract
        var token = command.token();

        // Validate presence and format
        if (token == null || !token.startsWith("Bearer ")) {
            throw new MissingJwtException(ErrorCode.AUTH_ERROR_JWT_MISSING);
        }

        // Validate and parse claims (JwtException handled by ControllerAdvice)
        var claims = jwtUtil.validateToken(token.substring(7));

        // Extract custom claims
        String email = claims.get("email", String.class);
        Long id = claims.get("id", Long.class);
        String name = claims.get("name", String.class);
        List<?> rawRoles = claims.get("roles", List.class);

        List<String> roles = rawRoles.stream()
                .map(Object::toString)  // convert each element to String safely
                .toList();

        // Parse and return the AuthDetailsDto
        return new AuthDetailsDto(id, email, name, roles);

    }
}
