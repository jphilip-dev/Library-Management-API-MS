package com.jphilips.library.book.service.query;

import com.jphilips.library.book.dto.BookResponseDto;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookQueryFacadeService {

    private final BookQueryService bookQueryService;

    public PagedResponse<BookResponseDto> getAllBooks(Pageable pageable) {
        return bookQueryService.getAllBooks(pageable);
    }

    public PagedResponse<BookResponseDto> searchBook(String query, Pageable pageable) {
        return bookQueryService.searchBook(query, pageable);
    }

    public BookResponseDto getBookById(Long id) {
        return bookQueryService.getBookById(id);
    }

    public BookResponseDto getBookByIsbn(String isbn) {
        return bookQueryService.getBookByIsbn(isbn);
    }
}
