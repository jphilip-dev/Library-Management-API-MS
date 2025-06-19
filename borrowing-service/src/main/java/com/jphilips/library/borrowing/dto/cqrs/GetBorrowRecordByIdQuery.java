package com.jphilips.library.borrowing.dto.cqrs;

import lombok.Builder;

@Builder
public record GetBorrowRecordByIdQuery(
        Long id
) {

}
