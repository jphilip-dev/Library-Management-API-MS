package com.jphilips.library.auth.service.helper;

import com.jphilips.library.auth.entity.User;
import com.jphilips.library.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserManager {

    private final Environment environment;
    private final UserRepository userRepository;

    public User save(User user) {
        // Add logging later (kafka?)
        return userRepository.save(user);
    }

    public boolean setInitialActivationState() {
        return Arrays.asList(environment.getActiveProfiles()).contains("dev");
    }

}
