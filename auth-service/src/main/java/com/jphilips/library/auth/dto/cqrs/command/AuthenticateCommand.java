package com.jphilips.library.auth.dto.cqrs.command;

import com.jphilips.library.auth.dto.LoginRequestDto;
import lombok.Builder;

@Builder
public record AuthenticateCommand(
        LoginRequestDto loginRequestDto
) {
}
