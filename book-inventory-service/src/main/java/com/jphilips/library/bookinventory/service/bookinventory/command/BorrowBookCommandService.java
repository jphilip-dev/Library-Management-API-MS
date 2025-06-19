package com.jphilips.library.bookinventory.service.bookinventory.command;

import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.BorrowBookCommand;
import com.jphilips.library.bookinventory.dto.mapper.BookInventoryMapper;
import com.jphilips.library.bookinventory.entity.Branch;
import com.jphilips.library.bookinventory.exception.custom.NotEnoughStockException;
import com.jphilips.library.bookinventory.service.branch.BranchManager;
import com.jphilips.library.bookinventory.service.bookinventory.BookInventoryManager;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BorrowBookCommandService implements Command<BorrowBookCommand, BookInventoryResponseDto> {

    private final BookInventoryManager bookInventoryManager;
    private final BranchManager branchManager;
    private final BookInventoryMapper bookInventoryMapper;

    @Override
    public BookInventoryResponseDto execute(BorrowBookCommand command) {

        // Extract Payload
        var bookId = command.bookId();
        var qtyToBorrow = command.qtyToBorrow();

        // Validate branch code, can throw an error
        Branch branch = branchManager.validateBranch(command.branchCode());

        // Check inventory if exists, else throw error
        var inventory = bookInventoryManager.validateByBookIdAndBranchCode(bookId, branch.getCode());

        if (inventory.getAvailableQuantity() < qtyToBorrow) {
            throw new NotEnoughStockException(ErrorCode.BOOK_INVENTORY_ERROR_NOT_ENOUGH_STOCK);
        }

        inventory.setAvailableQuantity(inventory.getAvailableQuantity() - qtyToBorrow);
        inventory.setBorrowed(inventory.getBorrowed() + qtyToBorrow);
        inventory.setUpdatedAt(LocalDateTime.now());

        bookInventoryManager.save(inventory);

        return bookInventoryMapper.toDto(inventory);
    }
}
