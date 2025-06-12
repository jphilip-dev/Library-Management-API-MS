package com.jphilips.library.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminUpdateRequestDto {

    @NotNull(message = "{required.notnull}")
    private Long id;

    @Email( message = "{email.invalid}")
    @NotBlank( message = "{email.blank}")
    private String email;

    @NotBlank(message = "{name.blank}")
    private String name;

    @NotBlank(message = "{password.blank}")
    @Size(min = 6, message = "{password.tooShort}")
    private String password;

    @NotNull(message = "{required.notnull}")
    private Boolean isActive;

    @NotNull(message = "{required.notnull}")
    private Boolean isAdmin;
}
