package com.jphilips.library.bookinventory.dto.cqrs;

import lombok.Builder;

@Builder
public record AddStockCommand(
        Long bookId,
        String branchCode,
        int quantityToAdd
) {
}
