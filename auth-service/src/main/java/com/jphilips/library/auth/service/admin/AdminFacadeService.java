package com.jphilips.library.auth.service.admin;

import com.jphilips.library.auth.dto.AdminUpdateRequestDto;
import com.jphilips.library.auth.dto.UserResponseDto;
import com.jphilips.library.auth.dto.cqrs.command.AdminUpdateUserCommand;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminFacadeService {

    private final UserQueryService userQueryService;
    private final AdminUpdateUserCommandService updateUserCommandService;

    public PagedResponse<UserResponseDto> fetchAllUsers(Pageable pageable){
        return userQueryService.getAllUsers(pageable);
    }

    public UserResponseDto fetchUserByEmail(String email){
        return userQueryService.getUserByEmail(email);
    }

    public UserResponseDto fetchUserById(Long id){
        return userQueryService.getUserById(id);
    }

    public UserResponseDto updateUser(AdminUpdateRequestDto adminUpdateRequestDto){

        var adminUpdateUserCommand = AdminUpdateUserCommand.builder()
                .adminUpdateRequestDTO(adminUpdateRequestDto)
                .build();

        return updateUserCommandService.execute(adminUpdateUserCommand);
    }

}
