package com.jphilips.library.bookinventory.service.branch.query;

import com.jphilips.library.bookinventory.dto.BranchResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.branch.GetAllBranchQuery;
import com.jphilips.library.bookinventory.dto.cqrs.branch.GetBranchByCodeQuery;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchQueryFacadeService {

    private final GetAllBranchQueryService getAllBranchQueryService;
    private final GetBranchByCodeQueryService getBranchByCodeQueryService;

    public PagedResponse<BranchResponseDto> getAllBranch(Pageable pageable) {

        var query = GetAllBranchQuery.builder()
                .pageable(pageable)
                .build();

        return getAllBranchQueryService.execute(query);
    }

    public BranchResponseDto getBranchByCode(String code){

        var query = GetBranchByCodeQuery.builder()
                .code(code)
                .build();

        return getBranchByCodeQueryService.execute(query);
    }
}
