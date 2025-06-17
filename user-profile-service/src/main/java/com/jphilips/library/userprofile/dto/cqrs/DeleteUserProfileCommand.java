package com.jphilips.library.userprofile.dto.cqrs;

import com.jphilips.shared.dto.AuthDetailsDto;

public record DeleteUserProfileCommand(
        AuthDetailsDto authDetailsDto,
        Long userProfileId
) {
}
