package com.jphilips.library.borrowing.config;

import com.jphilips.shared.dto.BookInventoryResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "book-inventory-service", url = "http://book-inventory-service:8084")
public interface BookInventoryClient {

    @PostMapping("/admin/inventory/borrow")
    BookInventoryResponseDto borrow(
            @RequestParam Long bookId,
            @RequestParam String branchCode,
            @RequestParam int qtyToBorrow);

    @PostMapping("/admin/inventory/return")
    BookInventoryResponseDto returnBook(
            @RequestParam Long bookId,
            @RequestParam String branchCode,
            @RequestParam int qtyToReturn
    );

    @GetMapping("/inventory/by-book-and-branch")
    BookInventoryResponseDto getByBookIdAndBranch(
            @RequestParam Long bookId,
            @RequestParam String branchCode);
}
