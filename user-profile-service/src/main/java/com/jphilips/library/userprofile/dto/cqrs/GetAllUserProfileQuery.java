package com.jphilips.library.userprofile.dto.cqrs;

import lombok.Builder;
import org.springframework.data.domain.Pageable;

@Builder
public record GetAllUserProfileQuery(
        Pageable pageable
) {
}
