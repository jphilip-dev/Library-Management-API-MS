package com.jphilips.library.bookinventory.controller;

import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.service.command.BookInventoryCommandFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/inventory")
public class BookInventoryCommandController {

    private final BookInventoryCommandFacadeService bookInventoryCommandFacadeService;

    @PostMapping("/add-stock")
    public ResponseEntity<BookInventoryResponseDto> addStock(
            @RequestParam Long bookId,
            @RequestParam String branchCode,
            @RequestParam int quantityToAdd
    ) {
        var response = bookInventoryCommandFacadeService.addStock(bookId, branchCode, quantityToAdd);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/borrow")
    public ResponseEntity<BookInventoryResponseDto> borrowBook(
            @RequestParam Long bookId,
            @RequestParam String branchCode,
            @RequestParam int qtyToBorrow
    ) {
        var response = bookInventoryCommandFacadeService.borrowBook(bookId, branchCode, qtyToBorrow);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/return")
    public ResponseEntity<BookInventoryResponseDto> returnBook(
            @RequestParam Long bookId,
            @RequestParam String branchCode,
            @RequestParam int qtyToReturn
    ) {
        var response = bookInventoryCommandFacadeService.returnBook(bookId, branchCode, qtyToReturn);
        return ResponseEntity.ok(response);
    }
}
