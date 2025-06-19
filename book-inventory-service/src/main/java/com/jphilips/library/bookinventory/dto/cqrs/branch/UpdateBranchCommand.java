package com.jphilips.library.bookinventory.dto.cqrs.branch;

import com.jphilips.library.bookinventory.dto.BranchRequestDto;
import lombok.Builder;

@Builder
public record UpdateBranchCommand(
        BranchRequestDto branchRequestDto
) {
}
