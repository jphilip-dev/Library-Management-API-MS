package com.jphilips.library.auth.controller;

import com.jphilips.library.auth.dto.AdminUpdateRequestDto;
import com.jphilips.library.auth.dto.UserResponseDto;
import com.jphilips.library.auth.service.admin.AdminFacadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminFacadeService adminFacadeService;

    // Fetches either a specific user by email or a paginated list of all users.
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(@RequestParam(required = false) String email, Pageable pageable) {
        if (email != null) {
            return ResponseEntity.ok(adminFacadeService.fetchUserByEmail(email));
        } else {
            return ResponseEntity.ok(adminFacadeService.fetchAllUsers(pageable));
        }
    }

    // Retrieves user details by user ID.
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(adminFacadeService.fetchUserById(id));
    }

    // Updates user information using admin privileges.
    // Requires a complete update request DTO in the request body.
    @PutMapping("/users")
    public ResponseEntity<UserResponseDto> updateUserById(@Valid @RequestBody AdminUpdateRequestDto adminUpdateRequestDto) {
        return ResponseEntity.ok(adminFacadeService.updateUser(adminUpdateRequestDto));
    }

}
