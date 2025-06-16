package com.jphilips.library.userprofile.service.command;

import com.jphilips.library.userprofile.dto.UserProfileResponseDto;
import com.jphilips.library.userprofile.dto.cqrs.UpdateUserProfileCommand;
import com.jphilips.library.userprofile.dto.mapper.UserProfileMapper;
import com.jphilips.library.userprofile.service.helper.UserProfileManager;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateUserProfileCommandService implements Command<UpdateUserProfileCommand, UserProfileResponseDto> {

    private final UserProfileManager userProfileManager;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponseDto execute(UpdateUserProfileCommand command) {

        // Extract Payload
        var authDetailsDto = command.authDetailsDto();
        var userProfileId = command.userProfileId();
        var userProfileRequestDto = command.userProfileRequestDto();

        // Ownership check
        userProfileManager.ownershipCheck(authDetailsDto, userProfileId);

        // Retrieve UserProfile, might throw exception
        var userProfile = userProfileManager.validateUserProfileById(userProfileId);

        // Update fields
        userProfile.setAddress(userProfileRequestDto.getAddress());
        userProfile.setPhoneNumber(userProfileRequestDto.getPhoneNumber());
        userProfile.setBirthDate(userProfileRequestDto.getBirthDate());
        userProfile.setUpdatedAt(LocalDateTime.now());

        // Convert to dto then return
        return userProfileMapper.toDto(userProfile);
    }
}
