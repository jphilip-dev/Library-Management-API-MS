package com.jphilips.library.userprofile.service.query;

import com.jphilips.library.userprofile.dto.UserProfileResponseDto;
import com.jphilips.library.userprofile.dto.cqrs.GetUserProfileByIdQuery;
import com.jphilips.library.userprofile.dto.mapper.UserProfileMapper;
import com.jphilips.library.userprofile.service.helper.UserProfileManager;
import com.jphilips.shared.util.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserProfileByIdQueryService implements Query<GetUserProfileByIdQuery, UserProfileResponseDto> {

    private final UserProfileManager userProfileManager;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponseDto execute(GetUserProfileByIdQuery query) {

        // Extract payload
        var authDetailsDto = query.authDetailsDto();
        var userProfileId = query.userProfileId();

        // Ownership check
        userProfileManager.ownershipCheck(authDetailsDto, userProfileId);

        // Retrieve user profile
        var userProfile = userProfileManager.validateUserProfileById(userProfileId);

        // Convert to dto then return
        return userProfileMapper.toDto(userProfile);
    }
}
