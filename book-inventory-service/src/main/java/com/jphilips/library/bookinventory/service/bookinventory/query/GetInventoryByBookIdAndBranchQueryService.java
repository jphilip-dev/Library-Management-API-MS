package com.jphilips.library.bookinventory.service.bookinventory.query;

import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.GetInventoryByBookIdAndBranchQuery;
import com.jphilips.library.bookinventory.dto.mapper.BookInventoryMapper;
import com.jphilips.library.bookinventory.service.bookinventory.BookInventoryManager;
import com.jphilips.shared.dto.BookInventoryResponseDto;
import com.jphilips.shared.util.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetInventoryByBookIdAndBranchQueryService implements Query<GetInventoryByBookIdAndBranchQuery, BookInventoryResponseDto> {

    private final BookInventoryManager bookInventoryManager;
    private final BookInventoryMapper bookInventoryMapper;

    @Override
    public BookInventoryResponseDto execute(GetInventoryByBookIdAndBranchQuery query) {

        var inventory = bookInventoryManager.validateByBookIdAndBranchCode(query.bookId(), query.branchCode());


        return bookInventoryMapper.toDto(inventory);
    }
}
