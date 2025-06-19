package com.jphilips.library.bookinventory.service.branch.query;

import com.jphilips.library.bookinventory.dto.BranchResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.branch.GetAllBranchQuery;
import com.jphilips.library.bookinventory.dto.mapper.BranchMapper;
import com.jphilips.library.bookinventory.entity.Branch;
import com.jphilips.library.bookinventory.repository.BranchRepository;
import com.jphilips.shared.dto.PagedResponse;
import com.jphilips.shared.util.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllBranchQueryService implements Query<GetAllBranchQuery, PagedResponse<BranchResponseDto>> {

    private final BranchMapper branchMapper;
    private final BranchRepository branchRepository;

    @Override
    public PagedResponse<BranchResponseDto> execute(GetAllBranchQuery query) {

        Page<Branch> branchPage = branchRepository.findAll(query.pageable());

        List<BranchResponseDto> content = branchPage.getContent().stream()
                .map(branchMapper::toDto)
                .toList();

        return new PagedResponse<>(content, branchPage);
    }
}
