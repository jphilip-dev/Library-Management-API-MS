package com.jphilips.library.auth.dto.cqrs.command;

import com.jphilips.library.auth.dto.UserRequestDto;
import com.jphilips.shared.dto.AuthDetailsDto;

public record UpdateUserCommand(
        AuthDetailsDto authDetailsDto,
        Long id,
        UserRequestDto userRequestDTO) {
}