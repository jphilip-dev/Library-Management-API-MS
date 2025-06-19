package com.jphilips.library.borrowing.service.query;

import com.jphilips.library.borrowing.dto.BorrowRecordResponseDto;
import com.jphilips.library.borrowing.dto.cqrs.GetAllBorrowRecordsQuery;
import com.jphilips.library.borrowing.dto.mapper.BorrowRecordMapper;
import com.jphilips.library.borrowing.entity.BorrowRecord;
import com.jphilips.library.borrowing.repository.BorrowRecordRepository;
import com.jphilips.shared.dto.PagedResponse;
import com.jphilips.shared.util.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllBorrowRecordQueryService implements Query<GetAllBorrowRecordsQuery, PagedResponse<BorrowRecordResponseDto>> {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BorrowRecordMapper borrowRecordMapper;

    @Override
    public PagedResponse<BorrowRecordResponseDto> execute(GetAllBorrowRecordsQuery query) {

        Page<BorrowRecord> borrowRecordPage = borrowRecordRepository.findAll(query.pageable());

        List<BorrowRecordResponseDto> content = borrowRecordPage.getContent().stream()
                .map(borrowRecordMapper::toDto)
                .toList();

        return new PagedResponse<>(content, borrowRecordPage);
    }
}
