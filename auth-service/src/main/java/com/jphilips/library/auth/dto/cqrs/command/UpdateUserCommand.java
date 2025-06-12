package com.jphilips.library.auth.dto.cqrs.command;

import com.jphilips.library.auth.dto.UserRequestDto;
import com.jphilips.shared.dto.RequestHeaderDetailsDto;

public record UpdateUserCommand(
        RequestHeaderDetailsDto requestHeaderDetailsDto,
        Long id,
        UserRequestDto userRequestDTO) {
}