package com.jphilips.library.userprofile.service.command;

import com.jphilips.library.userprofile.dto.UserProfileRequestDto;
import com.jphilips.library.userprofile.dto.UserProfileResponseDto;
import com.jphilips.library.userprofile.dto.cqrs.CreateUserProfileCommand;
import com.jphilips.library.userprofile.dto.cqrs.DeleteUserProfileCommand;
import com.jphilips.library.userprofile.dto.cqrs.UpdateUserProfileCommand;
import com.jphilips.shared.dto.AuthDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileCommandFacadeService {

    private final CreateUserProfileCommandService createUserProfileCommandService;
    private final UpdateUserProfileCommandService updateUserProfileCommandService;
    private final DeleteUserProfileCommandService deleteUserProfileCommandService;

    public UserProfileResponseDto createUserProfile(
            AuthDetailsDto authDetailsDto,
            UserProfileRequestDto userProfileRequestDto) {

        var command = new CreateUserProfileCommand(authDetailsDto, userProfileRequestDto);

        return createUserProfileCommandService.execute(command);
    }

    public UserProfileResponseDto updateUserProfile(
            AuthDetailsDto authDetailsDto,
            Long userProfileId,
            UserProfileRequestDto userProfileRequestDto) {

        var command = new UpdateUserProfileCommand(authDetailsDto, userProfileId, userProfileRequestDto);

        return updateUserProfileCommandService.execute(command);
    }

    public void deleteUserProfile(
            AuthDetailsDto authDetailsDto,
            Long userProfileId) {

        var command = new DeleteUserProfileCommand(authDetailsDto, userProfileId);

        deleteUserProfileCommandService.execute(command);
    }

}
