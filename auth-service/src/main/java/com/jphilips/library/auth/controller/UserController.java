package com.jphilips.library.auth.controller;

import com.jphilips.library.auth.dto.UserRequestDto;
import com.jphilips.library.auth.dto.UserResponseDto;
import com.jphilips.library.auth.service.user.UserFacadeService;
import com.jphilips.shared.validator.groups.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacadeService userFacadeService;

    // Endpoint to update a user.
    // Authenticated user details are passed via custom headers (usually extracted from token).
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @RequestHeader(value = "X-User-Id") Long headerId,
            @RequestHeader(value = "X-User-Email") String headerEmail,
            @RequestHeader(value = "X-User-Name") String headerName,
            @PathVariable Long id,
            @Validated(OnUpdate.class) @RequestBody UserRequestDto userRequestDto) {

        var updatedUser = userFacadeService.updateUser(
                headerId, headerEmail, headerName,
                id,
                userRequestDto
        );

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedUser);

    }
}
