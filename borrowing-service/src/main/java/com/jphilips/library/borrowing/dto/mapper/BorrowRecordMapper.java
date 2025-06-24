package com.jphilips.library.borrowing.dto.mapper;

import com.jphilips.library.borrowing.dto.BorrowRecordResponseDto;
import com.jphilips.library.borrowing.entity.BorrowRecord;
import org.springframework.stereotype.Component;

@Component
public class BorrowRecordMapper {
    public BorrowRecordResponseDto toDto(BorrowRecord borrowRecord){
        return BorrowRecordResponseDto.builder()
                .id(borrowRecord.getId())
                .userId(borrowRecord.getUserId())
                .inventoryId(borrowRecord.getInventoryId())
                .borrowedAt(borrowRecord.getBorrowedAt())
                .dueDate(borrowRecord.getDueDate())
                .returnedAt(borrowRecord.getReturnedAt())
                .status(borrowRecord.getStatus())
                .build();
    }
}
