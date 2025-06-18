package com.jphilips.library.bookinventory.controller;

import com.jphilips.library.bookinventory.dto.BranchRequestDto;
import com.jphilips.library.bookinventory.dto.BranchResponseDto;
import com.jphilips.library.bookinventory.service.branch.command.BranchCommandFacadeService;
import com.jphilips.shared.validator.groups.OnCreate;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/branch")
public class BranchAdminCommandController {

    private final BranchCommandFacadeService branchCommandFacadeService;

    @PostMapping
    public ResponseEntity<BranchResponseDto> createBranch(
            @Validated({OnCreate.class, Default.class})
            @RequestBody BranchRequestDto branchRequestDto){

        var response = branchCommandFacadeService.createBranch(branchRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    public ResponseEntity<BranchResponseDto> updateBranch(
            @Valid
            @RequestBody BranchRequestDto branchRequestDto){

        var response = branchCommandFacadeService.updateBranch(branchRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteBranch(@PathVariable String code){

        branchCommandFacadeService.deleteBranch(code);

        return ResponseEntity.noContent().build();
    }

}
