package com.jphilips.library.bookinventory.service.command;

import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.AddStockCommand;
import com.jphilips.library.bookinventory.dto.cqrs.BorrowBookCommand;
import com.jphilips.library.bookinventory.dto.cqrs.ReturnBookCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookInventoryCommandFacadeService {

    private final AddStockCommandService addStockCommandService;
    private final BorrowBookCommandService borrowBookCommandService;
    private final ReturnBookCommandService returnBookCommandService;

    public BookInventoryResponseDto addStock(Long bookId, String branchCode, int quantityToAdd) {
        var command = AddStockCommand.builder()
                .bookId(bookId)
                .branchCode(branchCode)
                .quantityToAdd(quantityToAdd)
                .build();

        return addStockCommandService.execute(command);
    }

    public BookInventoryResponseDto borrowBook(Long bookId, String branchCode, int qtyToBorrow) {
        var command = BorrowBookCommand.builder()
                .bookId(bookId)
                .branchCode(branchCode)
                .qtyToBorrow(qtyToBorrow)
                .build();

        return borrowBookCommandService.execute(command);
    }

    public BookInventoryResponseDto returnBook(Long bookId, String branchCode, int qtyToReturn) {
        var command = ReturnBookCommand.builder()
                .bookId(bookId)
                .branchCode(branchCode)
                .qtyToReturn(qtyToReturn)
                .build();

        return returnBookCommandService.execute(command);
    }
}
