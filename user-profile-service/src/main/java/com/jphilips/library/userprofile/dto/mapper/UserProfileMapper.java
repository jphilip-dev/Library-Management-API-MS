package com.jphilips.library.userprofile.dto.mapper;

import com.jphilips.library.userprofile.dto.UserProfileRequestDto;
import com.jphilips.library.userprofile.dto.UserProfileResponseDto;
import com.jphilips.library.userprofile.entity.UserProfile;
import org.springframework.stereotype.Component;

@Component
public class UserProfileMapper {

    public UserProfileResponseDto toDto(UserProfile userProfile){
        return UserProfileResponseDto.builder()
                .id(userProfile.getId())
                .address(userProfile.getAddress())
                .phoneNumber(userProfile.getPhoneNumber())
                .birthDate(userProfile.getBirthDate())
                .build();
    }

    public UserProfile toEntity(UserProfileRequestDto userProfileRequestDto){
        return UserProfile.builder()
                .address(userProfileRequestDto.getAddress())
                .phoneNumber(userProfileRequestDto.getPhoneNumber())
                .birthDate(userProfileRequestDto.getBirthDate())
                .build();
    }
}

