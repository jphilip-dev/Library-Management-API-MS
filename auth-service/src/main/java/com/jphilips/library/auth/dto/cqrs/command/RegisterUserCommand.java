package com.jphilips.library.auth.dto.cqrs.command;

import com.jphilips.library.auth.dto.UserRequestDto;

public record RegisterUserCommand(
        UserRequestDto userRequestDto
) {
}
