package com.jphilips.library.bookinventory.config;

import com.jphilips.shared.dto.BookResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", url = "http://book-service:8082")
public interface BookClient {

    @GetMapping("/books/{id}")
    BookResponseDto getBookById(@PathVariable("id") Long id);

}
