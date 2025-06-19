package com.jphilips.library.bookinventory.repository;

import com.jphilips.library.bookinventory.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, String> {
    Optional<Branch> findByCode(String code);
}
