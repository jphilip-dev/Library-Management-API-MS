package com.jphilips.library.userprofile.controller;

import com.jphilips.library.userprofile.dto.UserProfileResponseDto;
import com.jphilips.library.userprofile.service.query.UserProfileQueryFacadeService;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/user-profile")
@RequiredArgsConstructor
public class UserProfileAdminController {

    private final UserProfileQueryFacadeService userProfileQueryFacadeService;

    public PagedResponse<UserProfileResponseDto> getAllUserProfile(Pageable pageable){
        return userProfileQueryFacadeService.getAllUserProfile(pageable);
    }
}
