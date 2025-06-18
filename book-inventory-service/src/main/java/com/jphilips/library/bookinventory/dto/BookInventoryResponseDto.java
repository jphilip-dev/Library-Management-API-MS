package com.jphilips.library.bookinventory.dto;

import lombok.Builder;

@Builder
public record BookInventoryResponseDto(
        Long id,
        Long bookId,
        String branchCode,
        int totalQuantity,
        int availableQuantity,
        int borrowed
) {
}
