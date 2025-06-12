package com.jphilips.library.auth.dto.cqrs.command;

import lombok.Builder;

@Builder
public record ValidateTokenCommand(
        String token
) {
}
