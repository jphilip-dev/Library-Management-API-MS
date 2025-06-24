package com.jphilips.library.borrowing.dto.cqrs;

import lombok.Builder;

@Builder
public record UpdateBorrowRecordCommand(
        Long userId,
        Long bookId,
        String branch
) {

}
