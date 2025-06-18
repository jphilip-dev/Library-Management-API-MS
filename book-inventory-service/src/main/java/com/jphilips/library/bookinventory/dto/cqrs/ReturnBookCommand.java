package com.jphilips.library.bookinventory.dto.cqrs;

import lombok.Builder;

@Builder
public record ReturnBookCommand (
        Long bookId,
        int qtyToReturn,
        String branchCode
){
}
