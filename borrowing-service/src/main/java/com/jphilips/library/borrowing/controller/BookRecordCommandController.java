package com.jphilips.library.borrowing.controller;

import com.jphilips.library.borrowing.dto.BorrowRecordResponseDto;
import com.jphilips.library.borrowing.service.command.BookRecordCommandFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/borrow-records")
@RequiredArgsConstructor
public class BookRecordCommandController {

    private final BookRecordCommandFacadeService bookRecordCommandFacadeService;

    @PostMapping
    public ResponseEntity<BorrowRecordResponseDto> createBorrowRecord(
            @RequestParam Long userId,
            @RequestParam Long bookId,
            @RequestParam String branchCode) {

        var response = bookRecordCommandFacadeService.createBorrowRecord(userId, bookId, branchCode);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<BorrowRecordResponseDto> updateBorrowRecord(
            @RequestParam Long userId,
            @RequestParam Long bookId,
            @RequestParam String branchCode) {

        var response = bookRecordCommandFacadeService.updateBorrowRecord(userId, bookId, branchCode);
        return ResponseEntity.ok(response);
    }
}
