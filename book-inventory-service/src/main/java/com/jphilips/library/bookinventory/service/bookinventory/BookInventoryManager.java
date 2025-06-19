package com.jphilips.library.bookinventory.service.bookinventory;

import com.jphilips.library.bookinventory.entity.BookInventory;
import com.jphilips.library.bookinventory.exception.custom.BookInventoryNotFoundException;
import com.jphilips.library.bookinventory.repository.BookInventoryRepository;
import com.jphilips.library.bookinventory.repository.BranchRepository;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookInventoryManager {

    private final BookInventoryRepository bookInventoryRepository;
    public final BranchRepository branchRepository;

    public BookInventory save(BookInventory bookInventory){
        return bookInventoryRepository.save(bookInventory);
    }

    public void delete(BookInventory bookInventory){
        bookInventoryRepository.delete(bookInventory);
    }

    public BookInventory validateById(Long id){
        return bookInventoryRepository.findById(id)
                .orElseThrow();
    }

    public BookInventory validateByBookIdAndBranchCode(Long bookId, String branchCode){
        return bookInventoryRepository.findByBookIdAndBranch_Code(bookId, branchCode)
                .orElseThrow(() -> new BookInventoryNotFoundException(ErrorCode.BOOK_INVENTORY_ERROR_NOT_FOUND, bookId + branchCode));
    }

}
