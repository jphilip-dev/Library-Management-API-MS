package com.jphilips.library.borrowing.repository;

import com.jphilips.library.borrowing.entity.BorrowRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    Page<BorrowRecord> findByUserId(Long userId, Pageable pageable);

    Optional<BorrowRecord> findByUserIdAndInventoryId(Long userId, Long inventoryId);

}
