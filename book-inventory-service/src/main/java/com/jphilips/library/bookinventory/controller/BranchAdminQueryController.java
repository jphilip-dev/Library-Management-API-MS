package com.jphilips.library.bookinventory.controller;

import com.jphilips.library.bookinventory.dto.BranchResponseDto;
import com.jphilips.library.bookinventory.service.branch.query.BranchQueryFacadeService;
import com.jphilips.shared.dto.PagedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/branch")
public class BranchAdminQueryController {

    private final BranchQueryFacadeService branchQueryFacadeService;

    @GetMapping
    public PagedResponse<BranchResponseDto> getAllBranch(Pageable pageable){
        return branchQueryFacadeService.getAllBranch(pageable);
    }

    @GetMapping("/{code}")
    public BranchResponseDto getBranchByCode(@PathVariable String code){
        return branchQueryFacadeService.getBranchByCode(code);
    }

}
