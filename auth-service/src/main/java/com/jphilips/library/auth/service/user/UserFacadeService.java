package com.jphilips.library.auth.service.user;

import com.jphilips.library.auth.dto.UserRequestDto;
import com.jphilips.library.auth.dto.UserResponseDto;
import com.jphilips.library.auth.dto.cqrs.command.UpdateUserCommand;
import com.jphilips.shared.dto.AuthDetailsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacadeService {

    private final UpdateUserCommandService updateUserCommandService;

    // Handles updating a user by delegating to the UpdateUserCommandService.
    public UserResponseDto updateUser(
            Long headerId, String headerEmail, String headerName,
            Long id,
            UserRequestDto userRequestDto) {

        var updateUserCommand = UpdateUserCommand.builder()
                .authDetailsDto(new AuthDetailsDto(headerId, headerEmail, headerName))
                .id(id)
                .userRequestDTO(userRequestDto)
                .build();

        return updateUserCommandService.execute(updateUserCommand);
    }
}
