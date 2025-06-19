package com.jphilips.library.bookinventory.dto.cqrs.bookinventory;

import lombok.Builder;

@Builder
public record BorrowBookCommand(
        Long bookId,
        int qtyToBorrow,
        String branchCode
) {
}
