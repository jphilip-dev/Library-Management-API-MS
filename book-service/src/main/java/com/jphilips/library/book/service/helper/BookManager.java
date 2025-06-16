package com.jphilips.library.book.service.helper;

import com.jphilips.library.book.entity.Book;
import com.jphilips.library.book.exception.custom.BookNotFoundException;
import com.jphilips.library.book.repository.BookRepository;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookManager {

    private final BookRepository bookRepository;

    public Book save(Book book) {
        if (book.getId() == null) {
            log.info("Saving new Book: {}", book.getTitle());
        } else {
            log.info("Updating Book: {}", book.getTitle());
        }
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        log.info("Deleting Book: {}", book.getTitle());
        bookRepository.delete(book);
    }

    public Book validateBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOK_ERROR_NOT_FOUND, id.toString()));
    }

    public Book validateBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(ErrorCode.BOOK_ERROR_NOT_FOUND, isbn));
    }
}
