package com.jphilips.library.bookinventory.service.command;

import com.jphilips.library.bookinventory.config.BookClient;
import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.AddStockCommand;
import com.jphilips.library.bookinventory.dto.mapper.BookInventoryMapper;
import com.jphilips.library.bookinventory.entity.BookInventory;
import com.jphilips.library.bookinventory.enums.BranchCode;
import com.jphilips.library.bookinventory.exception.custom.BookClientException;
import com.jphilips.library.bookinventory.exception.custom.BookInventoryNotFoundException;
import com.jphilips.library.bookinventory.service.helper.BookInventoryManager;
import com.jphilips.shared.dto.BookResponseDto;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import com.jphilips.shared.util.Command;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddStockCommandService implements Command<AddStockCommand, BookInventoryResponseDto> {

    private final BookInventoryMapper bookInventoryMapper;
    private final BookInventoryManager bookInventoryManager;

    private final BookClient bookClient;


    @Override
    public BookInventoryResponseDto execute(AddStockCommand command) {

        // Extract Payload
        var bookId = command.bookId();
        var quantityToAdd = command.quantityToAdd();

        // Validate branch code, can throw an error
        BranchCode branchCode = bookInventoryManager.validateBranchCode(command.branchCode());

        // Rest call to check if book exists
        try {
            BookResponseDto bookResponseDto = bookClient.getBookById(bookId);
        } catch (FeignException ex) {
            log.error("Feign Exception:{}", ex.getMessage(), ex);
            throw new BookClientException(ErrorCode.BOOK_INVENTORY_ERROR_BOOK_NOT_FOUND);
        }

        // Check inventory if exists, else catch error and make new inventory row
        BookInventory inventory;
        try {
            inventory = bookInventoryManager.validateByBookIdAndBranchCode(bookId,branchCode);
        }catch (BookInventoryNotFoundException ex){
            inventory  = BookInventory.builder()
                    .bookId(bookId)
                    .branchCode(branchCode)
                    .totalQuantity(0)
                    .availableQuantity(0)
                    .borrowed(0)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
        }

        // update and set specific fields
        inventory.setTotalQuantity(inventory.getTotalQuantity() + quantityToAdd);
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() + quantityToAdd);
        inventory.setUpdatedAt(LocalDateTime.now());

        // Save inventory
        bookInventoryManager.save(inventory);

        // Convert and return
        return bookInventoryMapper.toDto(inventory);
    }
}

