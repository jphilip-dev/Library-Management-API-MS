package com.jphilips.shared.dto;

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
