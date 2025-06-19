package com.jphilips.library.bookinventory.service.branch.command;

import com.jphilips.library.bookinventory.dto.BranchResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.branch.UpdateBranchCommand;
import com.jphilips.library.bookinventory.dto.mapper.BranchMapper;
import com.jphilips.library.bookinventory.service.branch.BranchManager;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateBranchCommandService implements Command<UpdateBranchCommand, BranchResponseDto> {

    private final BranchManager branchManager;
    private final BranchMapper branchMapper;

    @Override
    public BranchResponseDto execute(UpdateBranchCommand command) {

        // Extract Payload
        var branchRequestDto = command.branchRequestDto();

        var branch = branchManager.validateBranch(branchRequestDto.getCode());

        branch.setName(branchRequestDto.getName());

        branchManager.save(branch);

        return branchMapper.toDto(branch);
    }
}
