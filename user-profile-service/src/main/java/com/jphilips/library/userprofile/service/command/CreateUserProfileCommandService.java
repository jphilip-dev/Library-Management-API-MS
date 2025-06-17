package com.jphilips.library.userprofile.service.command;

import com.jphilips.library.userprofile.dto.UserProfileResponseDto;
import com.jphilips.library.userprofile.dto.cqrs.CreateUserProfileCommand;
import com.jphilips.library.userprofile.dto.mapper.UserProfileMapper;
import com.jphilips.library.userprofile.service.helper.UserProfileManager;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateUserProfileCommandService implements Command<CreateUserProfileCommand, UserProfileResponseDto> {

    private final UserProfileManager userProfileManager;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponseDto execute(CreateUserProfileCommand command) {

        // Extract Payload
        var authDetailsDto = command.authDetailsDto();
        var userProfileRequestDto = command.userProfileRequestDto();

        // Convert dto to entity
        var newUserProfile = userProfileMapper.toEntity(userProfileRequestDto);

        // Set specific fields
        newUserProfile.setId(authDetailsDto.id());
        newUserProfile.setCreatedAt(LocalDateTime.now());
        newUserProfile.setUpdatedAt(newUserProfile.getCreatedAt());

        // Save using manager
        userProfileManager.save(newUserProfile);

        // Convert to dto and return
        return userProfileMapper.toDto(newUserProfile);
    }
}
