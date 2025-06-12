package com.jphilips.shared.dto;

import java.util.List;

public record AuthDetailsDto(
        Long id,
        String email,
        String name,
        List<String> roles
) {
    public  AuthDetailsDto(Long id, String email, String name){
        this(id, email, name, null);
    }
}
