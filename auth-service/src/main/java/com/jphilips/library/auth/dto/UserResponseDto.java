package com.jphilips.library.auth.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record UserResponseDto(Long id,
                              String email,
                              String name,
                              Boolean isActive,
                              List<String> roles) {
}
