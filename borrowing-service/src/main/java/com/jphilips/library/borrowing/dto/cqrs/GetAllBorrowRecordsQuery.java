package com.jphilips.library.borrowing.dto.cqrs;

import lombok.Builder;
import org.springframework.data.domain.Pageable;

@Builder
public record GetAllBorrowRecordsQuery(
        Pageable pageable
) {
}
