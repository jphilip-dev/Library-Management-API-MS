package com.jphilips.library.auth.dto.cqrs.command;

import com.jphilips.library.auth.dto.LoginRequestDto;

public record AuthenticateCommand(
        LoginRequestDto loginRequestDto
) {
}
