package com.jphilips.library.bookinventory.service.branch.command;

import com.jphilips.library.bookinventory.dto.cqrs.branch.DeleteBranchCommand;
import com.jphilips.library.bookinventory.service.branch.BranchManager;
import com.jphilips.shared.util.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBranchCommandService implements Command<DeleteBranchCommand, Void> {

    private final BranchManager branchManager;

    @Override
    public Void execute(DeleteBranchCommand command) {

        var branch = branchManager.validateBranch(command.code());

        branchManager.delete(branch);

        return null;
    }
}
