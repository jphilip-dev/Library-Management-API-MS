package com.jphilips.library.borrowing.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jphilips.library.borrowing.config.UserProfileClient;
import com.jphilips.library.borrowing.entity.BorrowRecord;
import com.jphilips.library.borrowing.exception.custom.BorrowException;
import com.jphilips.library.borrowing.repository.BorrowRecordRepository;
import com.jphilips.shared.dto.ExceptionResponseDto;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BorrowRecordManager {

    private final BorrowRecordRepository borrowRecordRepository;
    private final UserProfileClient userProfileClient;
    private final ObjectMapper objectMapper;

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

    public BorrowRecord validateByUserIdAndInventoryId(Long userId, Long inventoryId){
        return borrowRecordRepository.findByUserIdAndInventoryId(userId,inventoryId)
                .orElseThrow();
    }

    public void validateUserId(Long userId) {

        try {
            userProfileClient.validateUserProfileById(userId);
        } catch (FeignException ex) {
            log.error("Error feign call to profile: ", ex);
            try {
                ExceptionResponseDto errorResponse = objectMapper.readValue(ex.contentUTF8(), ExceptionResponseDto.class);
                String error = errorResponse.code();
                throw new BorrowException(ErrorCode.BORROWING_ERROR, error);
            } catch (JsonProcessingException e) {
                throw new BorrowException(ErrorCode.BORROWING_ERROR, null);
            }
        }

    }
}
