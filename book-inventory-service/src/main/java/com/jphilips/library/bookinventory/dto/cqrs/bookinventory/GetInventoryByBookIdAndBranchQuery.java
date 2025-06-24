package com.jphilips.library.bookinventory.dto.cqrs.bookinventory;

import lombok.Builder;

@Builder
public record GetInventoryByBookIdAndBranchQuery(
        Long bookId,
        String branchCode
) {
}
