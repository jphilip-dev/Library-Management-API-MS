package com.jphilips.library.bookinventory.service.query;

import com.jphilips.library.bookinventory.config.BookClient;
import com.jphilips.library.bookinventory.dto.BookInventoryResponseWithBookDto;
import com.jphilips.library.bookinventory.dto.cqrs.GetInventoryByIdWithBookQuery;
import com.jphilips.library.bookinventory.dto.mapper.BookInventoryMapper;
import com.jphilips.library.bookinventory.exception.custom.BookClientException;
import com.jphilips.library.bookinventory.service.helper.BookInventoryManager;
import com.jphilips.shared.dto.BookResponseDto;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import com.jphilips.shared.util.Query;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class GetInventoryByIdWithBookQueryService implements Query<GetInventoryByIdWithBookQuery, BookInventoryResponseWithBookDto> {

    private final BookInventoryManager bookInventoryManager;
    private final BookInventoryMapper bookInventoryMapper;
    private final BookClient bookClient;

    @Override
    public BookInventoryResponseWithBookDto execute(GetInventoryByIdWithBookQuery query) {
        // Extract payload
        var id = query.id();

        var bookInventory = bookInventoryManager.validateById(id);

        BookResponseDto bookResponseDto;

        // Rest call
        try {
            bookResponseDto = bookClient.getBookById(bookInventory.getBookId());
        } catch (FeignException ex) {
            log.error("Feign Exception:{}", ex.getMessage(), ex);
            throw new BookClientException(ErrorCode.BOOK_INVENTORY_ERROR_BOOK_NOT_FOUND);
        }

        return bookInventoryMapper.toDto(bookInventory, bookResponseDto);
    }
}
