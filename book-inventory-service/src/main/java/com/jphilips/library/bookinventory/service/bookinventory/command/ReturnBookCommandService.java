package com.jphilips.library.bookinventory.service.bookinventory.command;

import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.ReturnBookCommand;
import com.jphilips.library.bookinventory.dto.mapper.BookInventoryMapper;
import com.jphilips.library.bookinventory.entity.Branch;
import com.jphilips.library.bookinventory.service.branch.BranchManager;
import com.jphilips.library.bookinventory.service.bookinventory.BookInventoryManager;
import com.jphilips.shared.exception.custom.AppException;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReturnBookCommandService implements Command<ReturnBookCommand, BookInventoryResponseDto> {

    private final BookInventoryMapper bookInventoryMapper;
    private final BookInventoryManager bookInventoryManager;
    private final BranchManager branchManager;

    @Override
    public BookInventoryResponseDto execute(ReturnBookCommand command) {

        // Extract Payload
        var bookId = command.bookId();
        var qtyToReturn = command.qtyToReturn();

        // Validate branch code, can throw an error
        Branch branch = branchManager.validateBranch(command.branchCode());

        // Check inventory if exists, else throw error
        var inventory = bookInventoryManager.validateByBookIdAndBranchCode(bookId, branch.getCode());

        if (inventory.getBorrowed() < qtyToReturn) {
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        inventory.setAvailableQuantity(inventory.getAvailableQuantity() + qtyToReturn);
        inventory.setBorrowed(inventory.getBorrowed() - qtyToReturn);
        inventory.setUpdatedAt(LocalDateTime.now());

        bookInventoryManager.save(inventory);

        return bookInventoryMapper.toDto(inventory);
    }
}
