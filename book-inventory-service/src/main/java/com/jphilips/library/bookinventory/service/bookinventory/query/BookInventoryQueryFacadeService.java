package com.jphilips.library.bookinventory.service.bookinventory.query;


import com.jphilips.library.bookinventory.dto.BookInventoryResponseDto;
import com.jphilips.library.bookinventory.dto.BookInventoryResponseWithBookDto;
import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.GetAllInventoriesQuery;
import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.GetInventoriesByBookIdQuery;
import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.GetInventoryByIdQuery;
import com.jphilips.library.bookinventory.dto.cqrs.bookinventory.GetInventoryByIdWithBookQuery;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookInventoryQueryFacadeService {

    private final GetInventoryByIdQueryService getInventoryByIdQueryService;
    private final GetInventoriesByBookIdQueryService getInventoriesByBookIdQueryService;
    private final GetInventoryByIdWithBookQueryService getInventoryByIdWithBookQueryService;
    private final GetAllInventoriesQueryService getAllInventoriesQueryService;


    public PagedResponse<BookInventoryResponseDto> getAllInventories(Pageable pageable){
        var query = GetAllInventoriesQuery.builder()
                .pageable(pageable)
                .build();

        return getAllInventoriesQueryService.execute(query);
    }

    public BookInventoryResponseDto getById(Long id) {
        var query = GetInventoryByIdQuery.builder()
                .id(id)
                .build();

        return getInventoryByIdQueryService.execute(query);
    }

    public PagedResponse<BookInventoryResponseDto> getByBookId(Long bookId, Pageable pageable) {
        var query = GetInventoriesByBookIdQuery.builder()
                .bookId(bookId)
                .pageable(pageable)
                .build();

        return getInventoriesByBookIdQueryService.execute(query);
    }

    public BookInventoryResponseWithBookDto getByIdWithBook(Long id) {
        var query = GetInventoryByIdWithBookQuery.builder()
                .id(id)
                .build();

        return getInventoryByIdWithBookQueryService.execute(query);
    }
}
