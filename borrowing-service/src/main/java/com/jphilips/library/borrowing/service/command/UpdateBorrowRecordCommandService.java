package com.jphilips.library.borrowing.service.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jphilips.library.borrowing.config.BookInventoryClient;
import com.jphilips.library.borrowing.dto.BorrowRecordResponseDto;
import com.jphilips.library.borrowing.dto.cqrs.UpdateBorrowRecordCommand;
import com.jphilips.library.borrowing.dto.mapper.BorrowRecordMapper;
import com.jphilips.library.borrowing.enums.BorrowStatus;
import com.jphilips.library.borrowing.exception.custom.BorrowException;
import com.jphilips.library.borrowing.repository.BorrowRecordRepository;
import com.jphilips.library.borrowing.service.BorrowRecordManager;
import com.jphilips.shared.dto.BookInventoryResponseDto;
import com.jphilips.shared.dto.ExceptionResponseDto;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import com.jphilips.shared.util.Command;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateBorrowRecordCommandService implements Command<UpdateBorrowRecordCommand, BorrowRecordResponseDto> {

    private final BorrowRecordMapper borrowRecordMapper;
    private final BorrowRecordManager borrowRecordManager;
    private final BorrowRecordRepository borrowRecordRepository;

    private final BookInventoryClient bookInventoryClient;
    private final ObjectMapper objectMapper;

    @Override
    public BorrowRecordResponseDto execute(UpdateBorrowRecordCommand command) {

        var userId = command.userId();
        var bookId = command.bookId();
        var branch = command.branch();

        // validate user id from auth and profile
        borrowRecordManager.validateUserId(userId);

        // rest call get inventory
        BookInventoryResponseDto inventoryDetails;
        try {
            inventoryDetails = bookInventoryClient.getByBookIdAndBranch(bookId, branch);
        } catch (FeignException ex) {
            try {
                ExceptionResponseDto errorResponse = objectMapper.readValue(ex.contentUTF8(), ExceptionResponseDto.class);
                String error = errorResponse.code();
                throw new BorrowException(ErrorCode.BORROWING_ERROR, error);
            } catch (JsonProcessingException e) {
                throw new BorrowException(ErrorCode.BORROWING_ERROR, null);
            }
        }

        // validate record exist
        var record = borrowRecordManager.validateByUserIdAndInventoryId(userId, inventoryDetails.id());

        // rest call return
        BookInventoryResponseDto bookInventoryResponseDto;
        try {
            bookInventoryResponseDto = bookInventoryClient.returnBook(bookId, branch, 1);
        } catch (FeignException ex) {
            try {
                ExceptionResponseDto errorResponse = objectMapper.readValue(ex.contentUTF8(), ExceptionResponseDto.class);
                String error = errorResponse.code();
                throw new BorrowException(ErrorCode.BORROWING_ERROR, error);
            } catch (JsonProcessingException e) {
                throw new BorrowException(ErrorCode.BORROWING_ERROR, null);
            }
        }

        record.setReturnedAt(LocalDateTime.now());
        record.setStatus(BorrowStatus.RETURNED);

        borrowRecordManager.save(record);

        return borrowRecordMapper.toDto(record);
    }
}
