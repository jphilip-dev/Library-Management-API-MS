package com.jphilips.library.borrowing.service.query;

import com.jphilips.library.borrowing.dto.BorrowRecordResponseDto;
import com.jphilips.library.borrowing.dto.cqrs.GetBorrowRecordByIdQuery;
import com.jphilips.library.borrowing.dto.mapper.BorrowRecordMapper;
import com.jphilips.library.borrowing.service.BorrowRecordManager;
import com.jphilips.shared.util.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBorrowRecordByIdQueryService implements Query<GetBorrowRecordByIdQuery, BorrowRecordResponseDto> {

    private final BorrowRecordManager borrowRecordManager;
    private final BorrowRecordMapper borrowRecordMapper;

    @Override
    public BorrowRecordResponseDto execute(GetBorrowRecordByIdQuery query) {

        var record = borrowRecordManager.validateById(query.id());

        return borrowRecordMapper.toDto(record);
    }
}
