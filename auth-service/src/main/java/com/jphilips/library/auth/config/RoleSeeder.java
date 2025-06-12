package com.jphilips.library.auth.config;

import com.jphilips.library.auth.entity.Role;
import com.jphilips.library.auth.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void seedAdminRole() {
        if (roleRepository.findById("ADMIN").isEmpty()) {
            roleRepository.save(new Role("ADMIN", "Admin User Role"));
        }
        if (roleRepository.findById("USER").isEmpty()) {
            roleRepository.save(new Role("USER", "Normal User Role"));
        }
    }
}