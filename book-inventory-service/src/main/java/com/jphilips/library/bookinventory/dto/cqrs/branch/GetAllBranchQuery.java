package com.jphilips.library.bookinventory.dto.cqrs.branch;

import lombok.Builder;
import org.springframework.data.domain.Pageable;

@Builder
public record GetAllBranchQuery(
        Pageable pageable
) {
}
