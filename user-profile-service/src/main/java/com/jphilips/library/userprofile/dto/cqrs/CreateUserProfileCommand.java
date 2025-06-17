package com.jphilips.library.userprofile.dto.cqrs;

import com.jphilips.library.userprofile.dto.UserProfileRequestDto;
import com.jphilips.shared.dto.AuthDetailsDto;

public record CreateUserProfileCommand (
        AuthDetailsDto authDetailsDto,
        UserProfileRequestDto userProfileRequestDto
){
}
