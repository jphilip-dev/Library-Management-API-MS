package com.jphilips.library.book.dto;

import com.jphilips.library.book.validator.UniqueIsbn;
import com.jphilips.shared.validator.groups.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequestDto {

    @NotBlank
    @Size(min = 6)
    @UniqueIsbn(groups = OnCreate.class)
    private String isbn;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String publisher;

    @Positive
    private int yearPublished;

    @NotBlank
    private String category;

}
