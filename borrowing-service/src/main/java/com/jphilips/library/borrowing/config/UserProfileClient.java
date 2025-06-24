package com.jphilips.library.borrowing.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-profile-service", url = "http://user-profile-service:8083")
public interface UserProfileClient {

    @GetMapping("/internal/user-profile/{userProfileId}")
    void validateUserProfileById(@PathVariable Long userProfileId);

}
