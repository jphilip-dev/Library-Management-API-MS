package com.jphilips.library.userprofile.dto.cqrs;

import com.jphilips.shared.dto.AuthDetailsDto;
import lombok.Builder;

@Builder
public record GetUserProfileByIdQuery(
        AuthDetailsDto authDetailsDto,
        Long userProfileId
) {
}
