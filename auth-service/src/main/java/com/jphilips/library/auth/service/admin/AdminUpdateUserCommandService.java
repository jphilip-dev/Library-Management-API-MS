package com.jphilips.library.auth.service.admin;

import com.jphilips.library.auth.config.RoleSeeder;
import com.jphilips.library.auth.dto.UserResponseDto;
import com.jphilips.library.auth.dto.cqrs.command.AdminUpdateUserCommand;
import com.jphilips.library.auth.dto.mapper.UserMapper;
import com.jphilips.library.auth.exception.custom.ExistingEmailException;
import com.jphilips.library.auth.repository.UserRepository;
import com.jphilips.library.auth.service.helper.UserManager;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUpdateUserCommandService implements Command<AdminUpdateUserCommand, UserResponseDto> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserManager userManager;
    private final UserMapper userMapper;
    private final RoleSeeder roleSeeder;

    @Override
    public UserResponseDto execute(AdminUpdateUserCommand command) {

        // Extract payload
        var adminUpdateRequestDTO = command.adminUpdateRequestDTO();
        var id = adminUpdateRequestDTO.getId();

        // Validate user existence by ID (throws AppException if not found)
        var user = userManager.validateUserById(id);

        // check if the email has changed
        if (!user.getEmail().equalsIgnoreCase(adminUpdateRequestDTO.getEmail())){
            if (userRepository.findByEmail(adminUpdateRequestDTO.getEmail()).isPresent()){
                throw new ExistingEmailException(ErrorCode.AUTH_ERROR_USER_EMAIL_EXISTS, adminUpdateRequestDTO.getEmail());
            }
        }

        // Apply updates to user fields
        user.setEmail(adminUpdateRequestDTO.getEmail());
        user.setName(adminUpdateRequestDTO.getName());
        user.setPassword(passwordEncoder.encode(adminUpdateRequestDTO.getPassword()));
        user.setIsActive(adminUpdateRequestDTO.getIsActive());

        // Grant admin role if requested and not already present
        if (adminUpdateRequestDTO.getIsAdmin()) {
            if (!user.getRoles().contains(roleSeeder.getAdminRole())) {
                user.addRole(roleSeeder.getAdminRole());
            }
        }

        // Persist changes
        userRepository.save(user);

        // Return updated user as a DTO
        return userMapper.toDto(user);
    }
}
