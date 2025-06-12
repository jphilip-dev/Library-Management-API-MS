package com.jphilips.library.auth.service.user;

import com.jphilips.library.auth.dto.UserResponseDto;
import com.jphilips.library.auth.dto.cqrs.command.UpdateUserCommand;
import com.jphilips.library.auth.dto.mapper.UserMapper;
import com.jphilips.library.auth.service.helper.UserManager;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserCommandService implements Command<UpdateUserCommand, UserResponseDto> {

    private final UserManager userManager;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto execute(UpdateUserCommand command) {

        // Extract
        var requestHeaderDetails = command.requestHeaderDetailsDto();
        var id = command.id();
        var userRequestDto = command.userRequestDTO();

        // Get saved user details, will throw exception if id doesn't exist
        var savedUser = userManager.validateUserById(id);

        // Check ownership
        userManager.ownershipCheck(requestHeaderDetails, savedUser.getEmail());

        // Update fields, email field cannot be updated by user
        savedUser.setName(userRequestDto.getName());
        savedUser.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        // Save user
        userManager.save(savedUser);

        return userMapper.toDto(savedUser);
    }
}
