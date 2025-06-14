package com.jphilips.library.book.repository;

import com.jphilips.library.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    @Query("""
                SELECT b FROM Book b
                WHERE LOWER(b.isbn) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER(b.title) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER(b.author) LIKE LOWER(CONCAT('%', :query, '%'))
                   OR LOWER(b.publisher) LIKE LOWER(CONCAT('%', :query, '%'))
            """)
    Page<Book> search(@Param("query") String query, Pageable pageable);

}
