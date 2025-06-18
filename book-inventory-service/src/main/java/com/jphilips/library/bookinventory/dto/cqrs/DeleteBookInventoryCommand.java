package com.jphilips.library.bookinventory.dto.cqrs;

import lombok.Builder;

@Builder
public record DeleteBookInventoryCommand (
        Long id
){
}
