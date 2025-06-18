package com.jphilips.library.book.controller;

import com.jphilips.library.book.dto.BookRequestDto;
import com.jphilips.library.book.service.command.BookCommandFacadeService;
import com.jphilips.shared.dto.BookResponseDto;
import com.jphilips.shared.validator.groups.OnCreate;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/books")
@RequiredArgsConstructor
public class BookCommandController {

    private final BookCommandFacadeService bookCommandFacadeService;

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(
            @Validated({Default.class, OnCreate.class})
            @RequestBody BookRequestDto bookRequestDto) {
        var response = bookCommandFacadeService.createBook(bookRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequestDto bookRequestDto) {
        var response = bookCommandFacadeService.updateBook(id, bookRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookCommandFacadeService.deleteBook(id);
        return ResponseEntity.noContent().build(); // HTTP 204
    }
}
