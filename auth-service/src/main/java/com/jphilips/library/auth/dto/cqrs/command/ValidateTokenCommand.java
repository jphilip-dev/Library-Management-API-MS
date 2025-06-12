package com.jphilips.library.auth.dto.cqrs.command;

public record ValidateTokenCommand(
        String token
) {
}
