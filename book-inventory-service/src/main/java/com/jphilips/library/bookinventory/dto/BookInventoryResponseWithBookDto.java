package com.jphilips.library.bookinventory.dto;

import com.jphilips.shared.dto.BookResponseDto;
import lombok.Builder;

@Builder
public record BookInventoryResponseWithBookDto(
        Long id,
        String branchCode,
        int totalQuantity,
        int availableQuantity,
        int borrowed,
        BookResponseDto bookResponseDto
) {
}
