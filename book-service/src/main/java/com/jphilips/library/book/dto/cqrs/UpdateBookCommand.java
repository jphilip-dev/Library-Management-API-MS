package com.jphilips.library.book.dto.cqrs;

import com.jphilips.library.book.dto.BookRequestDto;

public record UpdateBookCommand(
        Long bookId,
        BookRequestDto bookRequestDto
) {}

