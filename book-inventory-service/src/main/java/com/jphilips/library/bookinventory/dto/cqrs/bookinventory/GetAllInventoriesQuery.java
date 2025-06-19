package com.jphilips.library.bookinventory.dto.cqrs.bookinventory;

import lombok.Builder;
import org.springframework.data.domain.Pageable;

@Builder
public record GetAllInventoriesQuery(
        Pageable pageable
) {
}
