package com.jphilips.library.auth.service.user;

import com.jphilips.library.auth.config.RoleSeeder;
import com.jphilips.library.auth.dto.UserResponseDto;
import com.jphilips.library.auth.dto.cqrs.command.CreateUserCommand;
import com.jphilips.library.auth.dto.mapper.UserMapper;
import com.jphilips.library.auth.service.helper.UserManager;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserCommandService implements Command<CreateUserCommand, UserResponseDto> {

    private final UserMapper userMapper;
    private final RoleSeeder roleSeeder;
    private final UserManager userManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto execute(CreateUserCommand command) {

        // Extract
        var userRequestDto = command.userRequestDto();

        // Map dto to entity
        var newUser = userMapper.toEntity(userRequestDto);

        // encrypt password
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        // set to Inactive. Dev environment override this to Active
        newUser.setIsActive(userManager.setInitialActivationState());

        // Add role
        newUser.addRole(roleSeeder.getDefaultRole());

        // Save
        var savedUser = userManager.save(newUser);

        return userMapper.toDto(savedUser);

    }
}
