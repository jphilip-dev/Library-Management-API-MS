package com.jphilips.library.auth.repository;

import com.jphilips.library.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
