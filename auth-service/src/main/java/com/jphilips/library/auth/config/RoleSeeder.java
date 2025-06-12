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

    private final String USER_ROLE = "USER";
    private final String ADMIN_ROLE = "ADMIN";

    @PostConstruct
    public void seedAdminRole() {
        if (roleRepository.findById(ADMIN_ROLE).isEmpty()) {
            roleRepository.save(new Role(ADMIN_ROLE, ADMIN_ROLE + " Role"));
        }
        if (roleRepository.findById(USER_ROLE).isEmpty()) {
            roleRepository.save(new Role(USER_ROLE, USER_ROLE + " Role"));
        }
    }

    public Role getDefaultRole(){
        return roleRepository.findById(USER_ROLE)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role: " + USER_ROLE));
    }

    public Role getAdminRole(){
        return roleRepository.findById(ADMIN_ROLE)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role: " + ADMIN_ROLE));
    }
}