package com.jphilips.library.book.controller;

import com.jphilips.library.book.dto.BookResponseDto;
import com.jphilips.library.book.service.query.BookQueryFacadeService;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookQueryController {

    private final BookQueryFacadeService bookQueryFacadeService;

    @GetMapping
    public PagedResponse<BookResponseDto> getAllBooks(Pageable pageable) {
        return bookQueryFacadeService.getAllBooks(pageable);
    }

    @GetMapping("/search")
    public PagedResponse<BookResponseDto> searchBooks(
            @RequestParam("q") String query,
            Pageable pageable
    ) {
        return bookQueryFacadeService.searchBook(query, pageable);
    }

    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable Long id) {
        return bookQueryFacadeService.getBookById(id);
    }

    @GetMapping("/isbn/{isbn}")
    public BookResponseDto getBookByIsbn(@PathVariable String isbn) {
        return bookQueryFacadeService.getBookByIsbn(isbn);
    }

}
