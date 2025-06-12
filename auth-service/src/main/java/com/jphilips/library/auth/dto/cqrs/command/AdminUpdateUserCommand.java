package com.jphilips.library.auth.dto.cqrs.command;

import com.jphilips.library.auth.dto.AdminUpdateRequestDto;

public record AdminUpdateUserCommand(
        AdminUpdateRequestDto adminUpdateRequestDTO
) {
}
