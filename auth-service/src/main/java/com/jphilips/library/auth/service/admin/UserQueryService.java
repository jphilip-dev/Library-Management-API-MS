package com.jphilips.library.auth.service.admin;

import com.jphilips.library.auth.dto.UserResponseDto;
import com.jphilips.library.auth.dto.mapper.UserMapper;
import com.jphilips.library.auth.entity.User;
import com.jphilips.library.auth.repository.UserRepository;
import com.jphilips.library.auth.service.helper.UserManager;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final UserManager userManager;

    public PagedResponse<UserResponseDto> getAllUsers(Pageable pageable){

        Page<User> userPage = userRepository.findAll(pageable);

        List<UserResponseDto> content = userPage.stream()
                .map(userMapper::toDto)
                .toList();

        return new PagedResponse<>(content, userPage);
    }
    public UserResponseDto getUserByEmail(String email){
        // May throw AppException (handled globally)
        var user = userManager.validateUserByEmail(email);
        return userMapper.toDto(user);
    }

    public UserResponseDto getUserById(Long id){
        // May throw AppException (handled globally)
        var user = userManager.validateUserById(id);
        return userMapper.toDto(user);
    }

}
