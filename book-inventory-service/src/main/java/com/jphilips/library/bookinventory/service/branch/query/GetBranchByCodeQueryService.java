package com.jphilips.library.bookinventory.service.branch.query;

import com.jphilips.library.bookinventory.dto.BranchResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.branch.GetBranchByCodeQuery;
import com.jphilips.library.bookinventory.dto.mapper.BranchMapper;
import com.jphilips.library.bookinventory.service.branch.BranchManager;
import com.jphilips.shared.util.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBranchByCodeQueryService implements Query<GetBranchByCodeQuery, BranchResponseDto> {

    private final BranchMapper branchMapper;
    private final BranchManager branchManager;

    @Override
    public BranchResponseDto execute(GetBranchByCodeQuery query) {

        var branch = branchManager.validateBranch(query.code());

        return branchMapper.toDto(branch);
    }
}
