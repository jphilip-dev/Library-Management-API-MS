package com.jphilips.library.book.service.command;

import com.jphilips.library.book.dto.cqrs.DeleteBookCommand;
import com.jphilips.library.book.service.helper.BookManager;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBookCommandService implements Command<DeleteBookCommand, Void> {

    private final BookManager bookManager;

    @Override
    public Void execute(DeleteBookCommand command) {
        Long bookId = command.bookId();

        // Validate book existence
        var book = bookManager.validateBookById(bookId);

        // Perform delete operation
        bookManager.delete(book);

        return null;
    }
}

