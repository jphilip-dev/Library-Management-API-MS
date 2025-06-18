package com.jphilips.library.bookinventory.service.branch.command;

import com.jphilips.library.bookinventory.dto.BranchRequestDto;
import com.jphilips.library.bookinventory.dto.BranchResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.branch.CreateBranchCommand;
import com.jphilips.library.bookinventory.dto.cqrs.branch.DeleteBranchCommand;
import com.jphilips.library.bookinventory.dto.cqrs.branch.UpdateBranchCommand;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchCommandFacadeService {

    private final CreateBranchCommandService createBranchCommandService;
    private final DeleteBranchCommandService deleteBranchCommandService;
    private final UpdateBranchCommandService updateBranchCommandService;

    public BranchResponseDto createBranch(BranchRequestDto branchRequestDto) {

        var command = CreateBranchCommand.builder()
                .branchRequestDto(branchRequestDto)
                .build();

        return createBranchCommandService.execute(command);
    }

    public BranchResponseDto updateBranch(BranchRequestDto branchRequestDto) {

        var command = UpdateBranchCommand.builder()
                .branchRequestDto(branchRequestDto)
                .build();

        return updateBranchCommandService.execute(command);
    }

    public void deleteBranch(String code){

        var command = DeleteBranchCommand.builder()
                .code(code)
                .build();

        deleteBranchCommandService.execute(command);
    }
}
