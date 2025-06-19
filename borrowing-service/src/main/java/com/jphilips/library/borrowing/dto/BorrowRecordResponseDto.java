package com.jphilips.library.borrowing.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jphilips.library.borrowing.enums.BorrowStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BorrowRecordResponseDto(
        Long id,
        Long userId,
        Long inventoryId,
        LocalDateTime borrowedAt,
        LocalDateTime dueDate,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDateTime returnedAt,

        BorrowStatus status
) {
}
