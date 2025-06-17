package com.jphilips.library.userprofile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileRequestDto {

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    @NotNull
    private LocalDate birthDate;
}
