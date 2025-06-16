package com.jphilips.library.book.dto.cqrs;

public record DeleteBookCommand(
        Long bookId
) {
}
