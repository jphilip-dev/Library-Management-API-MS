package com.jphilips.library.bookinventory.dto.cqrs.branch;

import lombok.Builder;

@Builder
public record DeleteBranchCommand(
        String code
) {
}
