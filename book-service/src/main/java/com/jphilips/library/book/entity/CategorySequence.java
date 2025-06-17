package com.jphilips.library.book.entity;

import com.jphilips.library.book.enums.BookCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category_sequence")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategorySequence {
    @Id
    @Enumerated(EnumType.STRING)
    private BookCategory category;

    @Column(nullable = false)
    private Long nextNumber;
}
