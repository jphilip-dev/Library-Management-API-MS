package com.jphilips.library.bookinventory.service.command;

import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.BorrowBookCommand;
import com.jphilips.library.bookinventory.dto.mapper.BookInventoryMapper;
import com.jphilips.library.bookinventory.enums.BranchCode;
import com.jphilips.library.bookinventory.exception.custom.NotEnoughStockException;
import com.jphilips.library.bookinventory.service.helper.BookInventoryManager;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BorrowBookCommandService implements Command<BorrowBookCommand, BookInventoryResponseDto> {

    private final BookInventoryManager bookInventoryManager;
    private final BookInventoryMapper bookInventoryMapper;

    @Override
    public BookInventoryResponseDto execute(BorrowBookCommand command) {

        // Extract Payload
        var bookId = command.bookId();
        var qtyToBorrow = command.qtyToBorrow();

        // Validate branch code, can throw an error
        BranchCode branchCode = bookInventoryManager.validateBranchCode(command.branchCode());

        // Check inventory if exists, else throw error
        var inventory = bookInventoryManager.validateByBookIdAndBranchCode(bookId,branchCode);

        if(inventory.getAvailableQuantity() < qtyToBorrow){
            throw new NotEnoughStockException(ErrorCode.BOOK_INVENTORY_ERROR_NOT_ENOUGH_STOCK);
        }

        inventory.setAvailableQuantity(inventory.getAvailableQuantity() - qtyToBorrow);
        inventory.setBorrowed(inventory.getBorrowed() + qtyToBorrow);
        inventory.setUpdatedAt(LocalDateTime.now());

        bookInventoryManager.save(inventory);

        return bookInventoryMapper.toDto(inventory);
    }
}
