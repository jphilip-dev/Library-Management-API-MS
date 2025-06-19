package com.jphilips.library.bookinventory.dto.mapper;

import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.dto.BookInventoryResponseWithBookDto;
import com.jphilips.library.bookinventory.entity.BookInventory;
import com.jphilips.shared.dto.BookResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BookInventoryMapper {

    public BookInventoryResponseDto toDto(BookInventory bookInventory){
        return BookInventoryResponseDto.builder()
                .id(bookInventory.getId())
                .bookId(bookInventory.getBookId())
                .branchCode(bookInventory.getBranch().getCode())
                .totalQuantity(bookInventory.getTotalQuantity())
                .availableQuantity(bookInventory.getAvailableQuantity())
                .borrowed(bookInventory.getBorrowed())
                .build();
    }

    public BookInventoryResponseWithBookDto toDto(BookInventory bookInventory, BookResponseDto bookResponseDto){
        return BookInventoryResponseWithBookDto.builder()
                .id(bookInventory.getId())
                .branchCode(bookInventory.getBranch().getCode())
                .totalQuantity(bookInventory.getTotalQuantity())
                .availableQuantity(bookInventory.getAvailableQuantity())
                .borrowed(bookInventory.getBorrowed())
                .bookResponseDto(bookResponseDto)
                .build();
    }

}
