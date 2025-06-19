package com.jphilips.library.bookinventory.service.bookinventory.query;

import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.GetAllInventoriesQuery;
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
public class GetAllInventoriesQueryService implements Query<GetAllInventoriesQuery, PagedResponse<BookInventoryResponseDto>> {

    private final BookInventoryRepository bookInventoryRepository;

    private final BookInventoryMapper bookInventoryMapper;

    @Override
    public PagedResponse<BookInventoryResponseDto> execute(GetAllInventoriesQuery query) {

        Page<BookInventory> bookInventoryPage = bookInventoryRepository.findAll(query.pageable());

        List<BookInventoryResponseDto> content = bookInventoryPage.getContent().stream()
                .map(bookInventoryMapper::toDto)
                .toList();

        return new PagedResponse<>(content,bookInventoryPage);
    }
}
