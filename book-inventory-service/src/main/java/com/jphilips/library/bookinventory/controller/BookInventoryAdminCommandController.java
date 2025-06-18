package com.jphilips.library.bookinventory.controller;

import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.service.command.BookInventoryCommandFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/inventory")
public class BookInventoryAdminCommandController {

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookInventory(@PathVariable Long id){

        bookInventoryCommandFacadeService.deleteBookInventory(id);

        return ResponseEntity.noContent().build();
    }
}
