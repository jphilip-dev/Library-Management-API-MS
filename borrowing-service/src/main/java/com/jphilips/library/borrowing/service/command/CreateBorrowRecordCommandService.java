package com.jphilips.library.borrowing.service.command;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jphilips.library.borrowing.config.BookInventoryClient;
import com.jphilips.library.borrowing.dto.BorrowRecordResponseDto;
import com.jphilips.library.borrowing.dto.cqrs.CreateBorrowRecordCommand;
import com.jphilips.library.borrowing.dto.mapper.BorrowRecordMapper;
import com.jphilips.library.borrowing.entity.BorrowRecord;
import com.jphilips.library.borrowing.enums.BorrowStatus;
import com.jphilips.library.borrowing.exception.custom.BorrowException;
import com.jphilips.library.borrowing.service.BorrowRecordManager;
import com.jphilips.shared.dto.BookInventoryResponseDto;
import com.jphilips.shared.dto.ExceptionResponseDto;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import com.jphilips.shared.util.Command;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateBorrowRecordCommandService implements Command<CreateBorrowRecordCommand, BorrowRecordResponseDto> {

    private final BorrowRecordMapper borrowRecordMapper;
    private final BorrowRecordManager borrowRecordManager;

    private final BookInventoryClient bookInventoryClient;
    private final ObjectMapper objectMapper;

    @Override
    public BorrowRecordResponseDto execute(CreateBorrowRecordCommand command) {

        var userId = command.userId();
        var bookId = command.bookId();
        var branch = command.branch();

        // validate user id from auth and profile
        log.info("validating user id..");
        borrowRecordManager.validateUserId(userId);
        log.info("user id validated");

        // rest call borrow
        BookInventoryResponseDto bookInventoryResponseDto;
        try {
            log.info("calling borrow endpoint");
            bookInventoryResponseDto = bookInventoryClient.borrow(bookId, branch, 1);
        } catch (FeignException ex) {
            log.info("feign exception..");
            try {
                log.info("parsing exception from feign {}", ex);
                ExceptionResponseDto errorResponse = objectMapper.readValue(ex.contentUTF8(), ExceptionResponseDto.class);
                String error = errorResponse.code();
                throw new BorrowException(ErrorCode.BORROWING_ERROR, error);
            } catch (JsonProcessingException e) {
                log.info("error parsing exception from feign: {}", e);
                throw new BorrowException(ErrorCode.BORROWING_ERROR, null);
            }
        }

        BorrowRecord newRecord = BorrowRecord.builder()
                .userId(userId)
                .inventoryId(bookInventoryResponseDto.id())
                .borrowedAt(LocalDateTime.now())
                .dueDate(LocalDateTime.now().plusDays(7))
                .returnedAt(null)
                .status(BorrowStatus.BORROWED)
                .build();

        borrowRecordManager.save(newRecord);

        return borrowRecordMapper.toDto(newRecord);
    }
}
