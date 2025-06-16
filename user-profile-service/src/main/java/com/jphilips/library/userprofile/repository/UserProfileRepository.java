package com.jphilips.library.userprofile.repository;

import com.jphilips.library.userprofile.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
