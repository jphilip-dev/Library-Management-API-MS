package com.jphilips.library.userprofile.controller;

import com.jphilips.library.userprofile.dto.UserProfileResponseDto;
import com.jphilips.library.userprofile.dto.cqrs.GetUserProfileByIdQuery;
import com.jphilips.library.userprofile.service.query.GetUserProfileByIdInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("internal/user-profile")
@RequiredArgsConstructor
public class UserProfileInternalController {

    private final GetUserProfileByIdInternalService getUserProfileByIdInternalService ;

    @GetMapping("/{userProfileId}")
    public UserProfileResponseDto getUserProfileById(@PathVariable Long userProfileId){
        return getUserProfileByIdInternalService.execute(
                new GetUserProfileByIdQuery(null, userProfileId)
        );
    }

}
