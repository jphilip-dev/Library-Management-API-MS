package com.jphilips.library.userprofile.controller;

import com.jphilips.library.userprofile.dto.UserProfileResponseDto;
import com.jphilips.library.userprofile.service.query.UserProfileQueryFacadeService;
import com.jphilips.shared.util.AuthHeaderParser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-profile")
@RequiredArgsConstructor
public class UserProfileQueryController {

    private final UserProfileQueryFacadeService userProfileQueryFacadeService;
    private final AuthHeaderParser authHeaderParser;

    @GetMapping("/{userProfileId}")
    public UserProfileResponseDto getUserProfileById(
            HttpServletRequest request,
            @PathVariable Long userProfileId){
        return userProfileQueryFacadeService.getUserProfileById(
                authHeaderParser.parse(request),
                userProfileId
        );
    }
}
