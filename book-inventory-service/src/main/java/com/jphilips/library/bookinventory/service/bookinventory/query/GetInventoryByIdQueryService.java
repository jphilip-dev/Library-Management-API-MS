package com.jphilips.library.bookinventory.service.bookinventory.query;

import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.GetInventoryByIdQuery;
import com.jphilips.library.bookinventory.dto.mapper.BookInventoryMapper;
import com.jphilips.library.bookinventory.service.bookinventory.BookInventoryManager;
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
