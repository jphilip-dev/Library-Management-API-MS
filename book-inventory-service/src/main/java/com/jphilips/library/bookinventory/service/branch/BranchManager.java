package com.jphilips.library.bookinventory.service.branch;

import com.jphilips.library.bookinventory.entity.Branch;
import com.jphilips.library.bookinventory.exception.custom.BranchNotFoundException;
import com.jphilips.library.bookinventory.repository.BranchRepository;
import com.jphilips.shared.exception.errorcode.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchManager {

    private final BranchRepository branchRepository;

    public Branch save(Branch branch){
        return branchRepository.save(branch);
    }

    public void delete(Branch branch){
        branchRepository.delete(branch);
    }

    public Branch validateBranch(String code){
        return branchRepository.findByCode(code.toUpperCase())
                .orElseThrow(() -> new BranchNotFoundException(ErrorCode.BOOK_INVENTORY_BRANCH_NOT_FOUND, code));
    }

}
