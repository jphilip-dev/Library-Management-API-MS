package com.jphilips.library.userprofile.service.query;

import com.jphilips.library.userprofile.dto.UserProfileResponseDto;
import com.jphilips.library.userprofile.dto.cqrs.GetAllUserProfileQuery;
import com.jphilips.library.userprofile.dto.mapper.UserProfileMapper;
import com.jphilips.library.userprofile.entity.UserProfile;
import com.jphilips.library.userprofile.repository.UserProfileRepository;
import com.jphilips.shared.dto.PagedResponse;
import com.jphilips.shared.util.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUserProfileQueryService implements Query<GetAllUserProfileQuery, PagedResponse<UserProfileResponseDto>> {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper;

    @Override
    public PagedResponse<UserProfileResponseDto> execute(GetAllUserProfileQuery query) {

        // Extract Payload
        var pageable = query.pageable();

        Page<UserProfile> userProfilePage = userProfileRepository.findAll(pageable);

        List<UserProfileResponseDto> content = userProfilePage.getContent().stream()
                .map(userProfileMapper::toDto)
                .toList();

        return new PagedResponse<>(content, userProfilePage);
    }
}
