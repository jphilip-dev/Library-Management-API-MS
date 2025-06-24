package com.jphilips.library.borrowing.service.query;

import com.jphilips.library.borrowing.dto.BorrowRecordResponseDto;
import com.jphilips.library.borrowing.dto.cqrs.GetAllBorrowRecordsQuery;
import com.jphilips.library.borrowing.dto.cqrs.GetBorrowRecordByIdQuery;
import com.jphilips.library.borrowing.dto.cqrs.GetBorrowRecordsByUserIdQuery;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookRecordQueryFacadeService {

    private final GetAllBorrowRecordQueryService getAllBorrowRecordQueryService;
    private final GetBorrowRecordByIdQueryService getBorrowRecordByIdQueryService;
    private final GetBorrowRecordsByUserIdQueryService getBorrowRecordsByUserIdQueryService;

    public PagedResponse<BorrowRecordResponseDto> getAllBorrowRecords(Pageable pageable){

        var query = GetAllBorrowRecordsQuery.builder()
                .pageable(pageable)
                .build();

        return getAllBorrowRecordQueryService.execute(query);
    }

    public PagedResponse<BorrowRecordResponseDto> getBorrowRecordsByUserId(Long userId, Pageable pageable){

        var query = GetBorrowRecordsByUserIdQuery.builder()
                .userId(userId)
                .pageable(pageable)
                .build();

        return getBorrowRecordsByUserIdQueryService.execute(query);
    }

    public BorrowRecordResponseDto getBorrowRecordById(Long id){

        var query = GetBorrowRecordByIdQuery.builder()
                .id(id)
                .build();

        return getBorrowRecordByIdQueryService.execute(query);
    }
}
