package com.jphilips.library.bookinventory.dto.cqrs.bookinventory;

import lombok.Builder;

@Builder
public record DeleteBookInventoryCommand (
        Long id
){
}
