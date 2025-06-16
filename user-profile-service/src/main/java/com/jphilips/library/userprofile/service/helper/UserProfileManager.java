package com.jphilips.library.userprofile.service.helper;

import com.jphilips.library.userprofile.entity.UserProfile;
import com.jphilips.library.userprofile.exception.custom.UserProfileNotFoundException;
import com.jphilips.library.userprofile.exception.custom.UserProfileOwnershipException;
import com.jphilips.library.userprofile.repository.UserProfileRepository;
import com.jphilips.shared.dto.AuthDetailsDto;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserProfileManager {

    private final UserProfileRepository userProfileRepository;

    public UserProfile save(UserProfile userProfile){
        return userProfileRepository.save(userProfile);
    }

    public void delete(UserProfile userProfile){
        userProfileRepository.delete(userProfile);
    }

    public void ownershipCheck(AuthDetailsDto authDetailsDto, Long userProfileId){
        if(!Objects.equals(authDetailsDto.id(), userProfileId)){
            throw  new UserProfileOwnershipException(ErrorCode.USER_PROFILE_ERROR_OWNERSHIP_MISMATCH);
        }
    }

    public UserProfile validateUserProfileById(Long userProfileId) {
        return userProfileRepository.findById(userProfileId)
                .orElseThrow(() -> new UserProfileNotFoundException(ErrorCode.USER_PROFILE_ERROR_NOT_FOUND));
    }
}
