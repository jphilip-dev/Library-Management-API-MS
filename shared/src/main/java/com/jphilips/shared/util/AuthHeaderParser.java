package com.jphilips.shared.util;

import com.jphilips.shared.dto.AuthDetailsDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthHeaderParser {

    default AuthDetailsDto parse(HttpServletRequest request) {
        Long id = Long.valueOf(request.getHeader("X-User-Id"));
        String email = request.getHeader("X-User-Email");
        String name = request.getHeader("X-User-Name");
        return new AuthDetailsDto(id, email, name);
    }
}