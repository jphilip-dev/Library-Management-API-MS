package com.jphilips.library.auth.service.helper;

import com.jphilips.library.auth.entity.User;
import com.jphilips.library.auth.exception.custom.OwnershipException;
import com.jphilips.library.auth.exception.custom.UserNotFoundException;
import com.jphilips.library.auth.repository.UserRepository;
import com.jphilips.shared.dto.RequestHeaderDetailsDto;
import com.jphilips.shared.exception.errorcode.ErrorCode;
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

    public User validateUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorCode.AUTH_ERROR_USER_NOT_FOUND, id.toString()));
    }

    public void ownershipCheck(RequestHeaderDetailsDto requestHeaderDetailsDto, String savedUserEmail){
        if (!savedUserEmail.equalsIgnoreCase(requestHeaderDetailsDto.email())){
            throw new OwnershipException(ErrorCode.AUTH_ERROR_OWNERSHIP_MISMATCH);
        }
    }
}
