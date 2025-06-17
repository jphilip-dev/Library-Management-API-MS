package com.jphilips.library.userprofile.controller;

import com.jphilips.library.userprofile.dto.UserProfileRequestDto;
import com.jphilips.library.userprofile.dto.UserProfileResponseDto;
import com.jphilips.library.userprofile.service.command.UserProfileCommandFacadeService;
import com.jphilips.shared.util.AuthHeaderParser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-profile")
@RequiredArgsConstructor
public class UserProfileCommandController {

    private final UserProfileCommandFacadeService userProfileCommandFacadeService;
    private final AuthHeaderParser authHeaderParser;

    @PostMapping
    public ResponseEntity<UserProfileResponseDto> createUserProfile(
            HttpServletRequest request,
            @Valid @RequestBody UserProfileRequestDto userProfileRequestDto
    ) {

        var userProfileResponseDto = userProfileCommandFacadeService.createUserProfile(
                authHeaderParser.parse(request),
                userProfileRequestDto
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(userProfileResponseDto);
    }

    @PutMapping("/{userProfileId}")
    public ResponseEntity<UserProfileResponseDto> updateUserProfile(
            @PathVariable Long userProfileId,
            HttpServletRequest request,
            @Valid @RequestBody UserProfileRequestDto userProfileRequestDto) {

        var updated = userProfileCommandFacadeService.updateUserProfile(
                authHeaderParser.parse(request),
                userProfileId,
                userProfileRequestDto
        );

        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{userProfileId}")
    public ResponseEntity<?> deleteUserProfile(
            @PathVariable Long userProfileId,
            HttpServletRequest request) {

        userProfileCommandFacadeService.deleteUserProfile(
                authHeaderParser.parse(request),
                userProfileId
        );

        return ResponseEntity.noContent().build();
    }
}
