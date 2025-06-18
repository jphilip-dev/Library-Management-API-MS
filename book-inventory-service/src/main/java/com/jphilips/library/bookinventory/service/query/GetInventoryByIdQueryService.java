package com.jphilips.library.bookinventory.service.query;

import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.GetInventoryByIdQuery;
import com.jphilips.library.bookinventory.dto.mapper.BookInventoryMapper;
import com.jphilips.library.bookinventory.service.helper.BookInventoryManager;
import com.jphilips.shared.util.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetInventoryByIdQueryService implements Query<GetInventoryByIdQuery, BookInventoryResponseDto> {

    private final BookInventoryManager bookInventoryManager;
    private final BookInventoryMapper bookInventoryMapper;

    @Override
    public BookInventoryResponseDto execute(GetInventoryByIdQuery query) {
        // Extract payload
        var id = query.id();

        var bookInventory = bookInventoryManager.validateById(id);
        return bookInventoryMapper.toDto(bookInventory);
    }
}
