package com.jphilips.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jphilips.shared.exception.util.Error;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ExceptionResponseDto(
        LocalDateTime timestamp,
        int status,
        String error,
        String code,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String value,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        List<Error> errors,
        String path) {
}
