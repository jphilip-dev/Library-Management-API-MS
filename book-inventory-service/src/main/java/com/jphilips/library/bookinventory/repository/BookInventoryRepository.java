package com.jphilips.library.bookinventory.repository;

import com.jphilips.library.bookinventory.entity.BookInventory;
import com.jphilips.library.bookinventory.enums.BranchCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookInventoryRepository extends JpaRepository<BookInventory, Long> {

    Page<BookInventory> findByBookId(Long bookId, Pageable pageable);

    Optional<BookInventory> findByBookIdAndBranchCode(Long bookId, BranchCode branchCode);

}
