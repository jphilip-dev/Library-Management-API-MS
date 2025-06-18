package com.jphilips.library.book.dto.mapper;

import com.jphilips.library.book.dto.BookRequestDto;
import com.jphilips.shared.dto.BookResponseDto;
import com.jphilips.library.book.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookResponseDto toDto(Book book){
        return BookResponseDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .yearPublished(book.getYearPublished())
                .category(book.getCategory().name())
                .callNumber(book.getCallNumber())
                .build();
    }

    public Book toEntity(BookRequestDto bookRequestDto){
        return Book.builder()
                .isbn(bookRequestDto.getIsbn())
                .title(bookRequestDto.getTitle())
                .author(bookRequestDto.getAuthor())
                .publisher(bookRequestDto.getPublisher())
                .yearPublished(bookRequestDto.getYearPublished())
                .build();
    }

}
