package com.jphilips.library.book.dto.cqrs;

import com.jphilips.library.book.dto.BookRequestDto;

public record CreateBookCommand (
    BookRequestDto bookRequestDto
){
}
