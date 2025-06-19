package com.jphilips.library.bookinventory.validator;

import com.jphilips.library.bookinventory.repository.BranchRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueBranchValidator implements ConstraintValidator<UniqueBranch, String> {
    private final BranchRepository branchRepository;

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        return code != null && branchRepository.findByCode(code).isEmpty();
    }
}
