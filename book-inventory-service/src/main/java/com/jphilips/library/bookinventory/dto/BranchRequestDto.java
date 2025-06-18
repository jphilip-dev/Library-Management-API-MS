package com.jphilips.library.bookinventory.dto;

import com.jphilips.library.bookinventory.validator.UniqueBranch;
import com.jphilips.shared.validator.groups.OnCreate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchRequestDto {

    @NotBlank
    @UniqueBranch(groups = OnCreate.class)
    private String code;

    @NotBlank
    private String name;
}
