package com.jphilips.library.book.service.command;

import com.jphilips.library.book.dto.BookResponseDto;
import com.jphilips.library.book.dto.cqrs.UpdateBookCommand;
import com.jphilips.library.book.dto.mapper.BookMapper;
import com.jphilips.library.book.repository.BookRepository;
import com.jphilips.library.book.service.helper.BookManager;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateBookCommandService implements Command<UpdateBookCommand, BookResponseDto> {

    private final BookManager bookManager;
    private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    @Override
    public BookResponseDto execute(UpdateBookCommand command) {
        var bookRequestDto = command.bookRequestDto();
        var bookId = command.bookId();

        // Fetch existing book
        var existingBook = bookManager.validateBookById(bookId);

        // Check if ISBN is updated
        if (!existingBook.getIsbn().equalsIgnoreCase(bookRequestDto.getIsbn())){
            if(bookRepository.findByIsbn(bookRequestDto.getIsbn()).isPresent()){
                throw new IllegalArgumentException(); // Custom exception later
            }
        }

        // Update fields
        existingBook.setIsbn(bookRequestDto.getIsbn());
        existingBook.setTitle(bookRequestDto.getTitle());
        existingBook.setAuthor(bookRequestDto.getAuthor());
        existingBook.setPublisher(bookRequestDto.getPublisher());
        existingBook.setYearPublished(bookRequestDto.getYearPublished());
        existingBook.setUpdatedAt(LocalDateTime.now());

        // Save updated book
        bookManager.save(existingBook);

        return bookMapper.toDto(existingBook);
    }
}
