package com.jphilips.library.userprofile.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserProfileResponseDto(
        Long id,
        String address,
        String phoneNumber,
        LocalDate birthDate
) {

}
