package com.jphilips.library.book.service.command;

import com.jphilips.library.book.dto.BookResponseDto;
import com.jphilips.library.book.dto.cqrs.CreateBookCommand;
import com.jphilips.library.book.dto.mapper.BookMapper;
import com.jphilips.library.book.service.helper.BookManager;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateBookCommandService implements Command<CreateBookCommand, BookResponseDto> {

    private final BookManager bookManager;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDto execute(CreateBookCommand command) {

        // Extract Payload
        var bookRequestDto = command.bookRequestDto();

        // Convert from dto to entity
        var newBook = bookMapper.toEntity(bookRequestDto);

        // set date logs
        newBook.setCreatedAt(LocalDateTime.now());
        newBook.setUpdatedAt(newBook.getCreatedAt());

        // Save book
        bookManager.save(newBook);

        return bookMapper.toDto(newBook);
    }
}
