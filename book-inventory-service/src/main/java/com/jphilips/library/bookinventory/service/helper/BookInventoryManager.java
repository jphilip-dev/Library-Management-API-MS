package com.jphilips.library.bookinventory.service.helper;

import com.jphilips.library.bookinventory.entity.BookInventory;
import com.jphilips.library.bookinventory.enums.BranchCode;
import com.jphilips.library.bookinventory.exception.custom.BookInventoryNotFoundException;
import com.jphilips.library.bookinventory.exception.custom.BranchCodeInvalidException;
import com.jphilips.library.bookinventory.repository.BookInventoryRepository;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookInventoryManager {

    private final BookInventoryRepository bookInventoryRepository;

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

    public BookInventory validateByBookIdAndBranchCode(Long bookId, BranchCode branchCode){
        return bookInventoryRepository.findByBookIdAndBranchCode(bookId, branchCode)
                .orElseThrow(() -> new BookInventoryNotFoundException(ErrorCode.BOOK_INVENTORY_ERROR_NOT_FOUND, bookId + branchCode.name()));
    }

    public BranchCode validateBranchCode(String branchcodeString){
        BranchCode branchCode;
        try {
            branchCode = BranchCode.valueOf(branchcodeString.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new BranchCodeInvalidException(ErrorCode.BOOK_INVENTORY_ERROR_INVALID_BRANCH_CODE);
        }

        return branchCode;
    }


}
