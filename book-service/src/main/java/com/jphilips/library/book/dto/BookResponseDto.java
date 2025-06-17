package com.jphilips.library.book.dto;

import lombok.Builder;

@Builder
public record BookResponseDto(
        Long id,
        String isbn,
        String title,
        String author,
        String publisher,
        int yearPublished,
        String category,
        String callNumber
) {
}
