package com.jphilips.library.book.service.command;

import com.jphilips.library.book.dto.BookRequestDto;
import com.jphilips.shared.dto.BookResponseDto;
import com.jphilips.library.book.dto.cqrs.CreateBookCommand;
import com.jphilips.library.book.dto.cqrs.DeleteBookCommand;
import com.jphilips.library.book.dto.cqrs.UpdateBookCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookCommandFacadeService {

    private final CreateBookCommandService createBookCommandService;
    private final UpdateBookCommandService updateBookCommandService;
    private final DeleteBookCommandService deleteBookCommandService;


    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        // Wrap request DTO in a command object
        var command = new CreateBookCommand(bookRequestDto);

        // Delegate to service
        return createBookCommandService.execute(command);
    }

    public BookResponseDto updateBook(Long bookId, BookRequestDto bookRequestDto) {
        var command = new UpdateBookCommand(bookId, bookRequestDto);
        return updateBookCommandService.execute(command);
    }

    public void deleteBook(Long bookId) {
        var command = new DeleteBookCommand(bookId);
        deleteBookCommandService.execute(command);
    }
}
