package com.jphilips.library.bookinventory.controller;

import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.dto.BookInventoryResponseWithBookDto;
import com.jphilips.library.bookinventory.service.bookinventory.query.BookInventoryQueryFacadeService;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")
public class BookInventoryQueryController {

    private final BookInventoryQueryFacadeService bookInventoryQueryFacadeService;

    @GetMapping("/{id}")
    public ResponseEntity<BookInventoryResponseDto> getById(@PathVariable Long id) {
        var response = bookInventoryQueryFacadeService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/with-book/{id}")
    public ResponseEntity<BookInventoryResponseWithBookDto> getByIdWithBook(@PathVariable Long id) {
        var response = bookInventoryQueryFacadeService.getByIdWithBook(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-book/{bookId}")
    public ResponseEntity<PagedResponse<BookInventoryResponseDto>> getByBookId(
            @PathVariable Long bookId,
            Pageable pageable
    ) {
        var response = bookInventoryQueryFacadeService.getByBookId(bookId, pageable);
        return ResponseEntity.ok(response);
    }
}

