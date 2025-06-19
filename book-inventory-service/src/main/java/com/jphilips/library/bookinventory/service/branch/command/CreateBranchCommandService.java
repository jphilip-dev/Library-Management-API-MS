package com.jphilips.library.bookinventory.service.branch.command;

import com.jphilips.library.bookinventory.dto.BranchResponseDto;
import com.jphilips.library.bookinventory.dto.cqrs.branch.CreateBranchCommand;
import com.jphilips.library.bookinventory.dto.mapper.BranchMapper;
import com.jphilips.library.bookinventory.service.branch.BranchManager;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBranchCommandService implements Command<CreateBranchCommand, BranchResponseDto> {

    private final BranchManager branchManager;
    private final BranchMapper branchMapper;

    @Override
    public BranchResponseDto execute(CreateBranchCommand command) {

        var branchRequestDto = command.branchRequestDto();

        var newBranch = branchMapper.toEntity(branchRequestDto);

        branchManager.save(newBranch);

        return branchMapper.toDto(newBranch);
    }
}
