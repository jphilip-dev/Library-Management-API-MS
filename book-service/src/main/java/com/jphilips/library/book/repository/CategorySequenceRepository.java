package com.jphilips.library.book.repository;

import com.jphilips.library.book.entity.CategorySequence;
import com.jphilips.library.book.enums.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorySequenceRepository extends JpaRepository<CategorySequence, BookCategory> {
}
