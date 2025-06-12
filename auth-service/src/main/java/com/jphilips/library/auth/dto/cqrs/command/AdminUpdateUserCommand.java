package com.jphilips.library.auth.dto.cqrs.command;

import com.jphilips.library.auth.dto.AdminUpdateRequestDto;
import lombok.Builder;

@Builder
public record AdminUpdateUserCommand(
        AdminUpdateRequestDto adminUpdateRequestDTO
) {
}
