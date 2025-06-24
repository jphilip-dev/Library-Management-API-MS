package com.jphilips.library.borrowing.dto.cqrs;

import lombok.Builder;

@Builder
public record CreateBorrowRecordCommand(
        Long userId,
        Long bookId,
        String branch
) {
}
