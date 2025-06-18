package com.jphilips.library.bookinventory.dto;

import lombok.Builder;

@Builder
public record BranchResponseDto(
        String code,
        String name
) {
}
