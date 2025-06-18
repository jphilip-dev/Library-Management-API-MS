package com.jphilips.library.bookinventory.entity;

import com.jphilips.library.bookinventory.enums.BranchCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "book_inventory",
        uniqueConstraints = @UniqueConstraint(columnNames = {"bookId", "branchCode"}))
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BranchCode branchCode;

    @Column(nullable = false)
    private int totalQuantity;

    @Column(nullable = false)
    private int availableQuantity;

    @Column(nullable = false)
    private int borrowed;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
