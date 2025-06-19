package com.jphilips.library.bookinventory.controller;

import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.service.bookinventory.query.BookInventoryQueryFacadeService;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/inventory")
public class BookInventoryAdminQueryController {

    private final BookInventoryQueryFacadeService bookInventoryQueryFacadeService;

    @GetMapping()
    public ResponseEntity<PagedResponse<BookInventoryResponseDto>> getAllInventories(Pageable pageable) {
        var response = bookInventoryQueryFacadeService.getAllInventories(pageable);
        return ResponseEntity.ok(response);
    }

}
