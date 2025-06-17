package com.jphilips.library.userprofile.service.command;

import com.jphilips.library.userprofile.dto.cqrs.DeleteUserProfileCommand;
import com.jphilips.library.userprofile.service.helper.UserProfileManager;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserProfileCommandService implements Command<DeleteUserProfileCommand, Void> {

    private final UserProfileManager userProfileManager;

    @Override
    public Void execute(DeleteUserProfileCommand command) {

        // Extract Payload
        var authDetailsDto = command.authDetailsDto();
        var userProfileId = command.userProfileId();

        // Ownership check
        userProfileManager.ownershipCheck(authDetailsDto, userProfileId);

        // Retrieve UserProfile
        var userProfile = userProfileManager.validateUserProfileById(userProfileId);

        // Delete using manager
        userProfileManager.delete(userProfile);

        return null; // Required
    }
}
