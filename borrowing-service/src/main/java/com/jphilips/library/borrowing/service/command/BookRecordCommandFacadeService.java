package com.jphilips.library.borrowing.service.command;

import com.jphilips.library.borrowing.dto.BorrowRecordResponseDto;
import com.jphilips.library.borrowing.dto.cqrs.CreateBorrowRecordCommand;
import com.jphilips.library.borrowing.dto.cqrs.UpdateBorrowRecordCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookRecordCommandFacadeService {

    private final CreateBorrowRecordCommandService createBorrowRecordCommandService;
    private final UpdateBorrowRecordCommandService updateBorrowRecordCommandService;

    public BorrowRecordResponseDto createBorrowRecord(Long userId, Long bookId, String branchCode){

        var command = CreateBorrowRecordCommand.builder()
                .userId(userId)
                .bookId(bookId)
                .branch(branchCode)
                .build();
        return createBorrowRecordCommandService.execute(command);
    }

    public BorrowRecordResponseDto updateBorrowRecord(Long userId, Long bookId, String branchCode){

        var command = UpdateBorrowRecordCommand.builder()
                .userId(userId)
                .bookId(bookId)
                .branch(branchCode)
                .build();
        return updateBorrowRecordCommandService.execute(command);
    }

}
