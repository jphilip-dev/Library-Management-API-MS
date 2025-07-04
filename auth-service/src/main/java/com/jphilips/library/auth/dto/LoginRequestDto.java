package com.jphilips.library.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDto(
        @Email(message = "{email.invalid}")
        @NotBlank(message = "{email.blank}")
        String email,

        @NotBlank(message = "{password.blank}")
        @Size(min = 6, message = "{password.tooShort}")
        String password) {
}