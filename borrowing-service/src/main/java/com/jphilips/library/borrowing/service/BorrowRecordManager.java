package com.jphilips.library.borrowing.service;

import com.jphilips.library.borrowing.entity.BorrowRecord;
import com.jphilips.library.borrowing.repository.BorrowRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowRecordManager {

    private final BorrowRecordRepository borrowRecordRepository;

    public BorrowRecord save (BorrowRecord borrowRecord){
        return borrowRecordRepository.save(borrowRecord);
    }

    public void delete(BorrowRecord borrowRecord){
        borrowRecordRepository.delete(borrowRecord);
    }

    public BorrowRecord validateById(Long id){
        return borrowRecordRepository.findById(id)
                .orElseThrow();
    }

    public void validateUserId(Long userId) {
        // Rest call
    }
}
