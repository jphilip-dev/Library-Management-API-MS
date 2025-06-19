package com.jphilips.library.bookinventory.dto.mapper;

import com.jphilips.library.bookinventory.dto.BranchRequestDto;
import com.jphilips.library.bookinventory.dto.BranchResponseDto;
import com.jphilips.library.bookinventory.entity.Branch;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {
    public BranchResponseDto toDto(Branch branch){
        return BranchResponseDto.builder()
                .code(branch.getCode())
                .name(branch.getName())
                .build();
    }

    public Branch toEntity(BranchRequestDto branchRequestDto){
        return Branch.builder()
                .code(branchRequestDto.getCode())
                .name(branchRequestDto.getName())
                .build();
    }
}
