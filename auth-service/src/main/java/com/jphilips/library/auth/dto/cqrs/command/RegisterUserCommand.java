package com.jphilips.library.auth.dto.cqrs.command;

import com.jphilips.library.auth.dto.UserRequestDto;
import lombok.Builder;

@Builder
public record RegisterUserCommand(
        UserRequestDto userRequestDto
) {
}
