package com.jphilips.library.bookinventory.service.bookinventory.command;

import com.jphilips.shared.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.AddStockCommand;
import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.BorrowBookCommand;
import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.DeleteBookInventoryCommand;
import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.ReturnBookCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookInventoryCommandFacadeService {

    private final AddStockCommandService addStockCommandService;
    private final BorrowBookCommandService borrowBookCommandService;
    private final ReturnBookCommandService returnBookCommandService;
    private final DeleteBookInventoryCommandService deleteBookInventoryCommandService;

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

    public void deleteBookInventory(Long id) {

        var command = DeleteBookInventoryCommand.builder()
                .id(id)
                .build();

        deleteBookInventoryCommandService.execute(command);
    }
}
