package com.jphilips.library.bookinventory.service.bookinventory.query;

import com.jphilips.shared.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.GetInventoriesByBookIdQuery;
import com.jphilips.library.bookinventory.dto.mapper.BookInventoryMapper;
import com.jphilips.library.bookinventory.entity.BookInventory;
import com.jphilips.library.bookinventory.repository.BookInventoryRepository;
import com.jphilips.shared.dto.PagedResponse;
import com.jphilips.shared.util.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetInventoriesByBookIdQueryService
        implements Query<GetInventoriesByBookIdQuery, PagedResponse<BookInventoryResponseDto>> {

    private final BookInventoryRepository bookInventoryRepository;
    private final BookInventoryMapper bookInventoryMapper;

    @Override
    public PagedResponse<BookInventoryResponseDto> execute(GetInventoriesByBookIdQuery query) {

        // Extract payload
        var bookId = query.bookId();
        var pageable = query.pageable();

        Page<BookInventory> bookInventoryPage = bookInventoryRepository.findByBookId(bookId, pageable);

        List<BookInventoryResponseDto> content = bookInventoryPage.getContent().stream()
                .map(bookInventoryMapper::toDto)
                .toList();

        return new PagedResponse<>(content, bookInventoryPage);
    }
}
