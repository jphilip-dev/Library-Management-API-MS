package com.jphilips.library.bookinventory.dto.cqrs.bookinventory;

import lombok.Builder;

@Builder
public record ReturnBookCommand (
        Long bookId,
        int qtyToReturn,
        String branchCode
){
}
