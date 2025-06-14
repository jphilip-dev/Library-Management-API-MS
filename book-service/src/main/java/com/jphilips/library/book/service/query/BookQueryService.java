package com.jphilips.library.book.service.query;

import com.jphilips.library.book.dto.BookResponseDto;
import com.jphilips.library.book.dto.mapper.BookMapper;
import com.jphilips.library.book.entity.Book;
import com.jphilips.library.book.repository.BookRepository;
import com.jphilips.library.book.service.helper.BookManager;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookQueryService {

    private final BookManager bookManager;
    private final BookMapper bookMapper;

    private final BookRepository bookRepository;

    public PagedResponse<BookResponseDto> getAllBooks(Pageable pageable) {

        Page<Book> bookPage = bookRepository.findAll(pageable);

        List<BookResponseDto> content = bookPage.getContent().stream()
                .map(bookMapper::toDto)
                .toList();

        return new PagedResponse<>(content, bookPage);
    }

    public PagedResponse<BookResponseDto> searchBook(String query, Pageable pageable) {

        Page<Book> bookPage = bookRepository.search(query, pageable);

        List<BookResponseDto> content = bookPage.getContent().stream()
                .map(bookMapper::toDto)
                .toList();

        return new PagedResponse<>(content, bookPage);
    }

    public BookResponseDto getBookById(Long id) {

        var book = bookManager.validateBookById(id);

        return bookMapper.toDto(book);
    }

    public BookResponseDto getBookByIsbn(String isbn) {

        var book = bookManager.validateBookByIsbn(isbn);

        return bookMapper.toDto(book);
    }
}
