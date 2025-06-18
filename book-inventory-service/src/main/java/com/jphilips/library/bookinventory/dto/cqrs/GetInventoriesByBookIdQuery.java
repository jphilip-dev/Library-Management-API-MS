package com.jphilips.library.bookinventory.dto.cqrs;

import lombok.Builder;
import org.springframework.data.domain.Pageable;

@Builder
public record GetInventoriesByBookIdQuery(
        Long bookId,
        Pageable pageable
) {
}
