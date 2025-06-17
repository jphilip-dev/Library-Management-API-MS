package com.jphilips.library.userprofile.service.query;

import com.jphilips.library.userprofile.dto.UserProfileResponseDto;
import com.jphilips.library.userprofile.dto.cqrs.GetAllUserProfileQuery;
import com.jphilips.library.userprofile.dto.cqrs.GetUserProfileByIdQuery;
import com.jphilips.shared.dto.AuthDetailsDto;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileQueryFacadeService {

    private final GetAllUserProfileQueryService getAllUserProfileQueryService;
    private final GetUserProfileByIdQueryService getUserProfileByIdQueryService;

    public PagedResponse<UserProfileResponseDto> getAllUserProfile(Pageable pageable){

        var query = GetAllUserProfileQuery.builder()
                .pageable(pageable)
                .build();

        return getAllUserProfileQueryService.execute(query);
    }

    public UserProfileResponseDto getUserProfileById(
            AuthDetailsDto authDetailsDto,
            Long userProfileId){

        var query = GetUserProfileByIdQuery.builder()
                .authDetailsDto(authDetailsDto)
                .userProfileId(userProfileId)
                .build();

        return getUserProfileByIdQueryService.execute(query);
    }

}
