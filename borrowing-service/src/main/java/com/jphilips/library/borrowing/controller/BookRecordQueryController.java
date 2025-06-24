package com.jphilips.library.borrowing.controller;

import com.jphilips.library.borrowing.dto.BorrowRecordResponseDto;
import com.jphilips.library.borrowing.service.query.BookRecordQueryFacadeService;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow-records")
@RequiredArgsConstructor
public class BookRecordQueryController {

    private final BookRecordQueryFacadeService bookRecordQueryFacadeService;

    @GetMapping
    public ResponseEntity<PagedResponse<BorrowRecordResponseDto>> getAllBorrowRecords(
            Pageable pageable) {

        var response = bookRecordQueryFacadeService.getAllBorrowRecords(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowRecordResponseDto> getBorrowRecordById(@PathVariable Long id) {
        var response = bookRecordQueryFacadeService.getBorrowRecordById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    public ResponseEntity<PagedResponse<BorrowRecordResponseDto>> getBorrowRecordsByUserId(
            @RequestParam Long userId,Pageable pageable) {

        var response = bookRecordQueryFacadeService.getBorrowRecordsByUserId(userId, pageable);
        return ResponseEntity.ok(response);
    }
}
