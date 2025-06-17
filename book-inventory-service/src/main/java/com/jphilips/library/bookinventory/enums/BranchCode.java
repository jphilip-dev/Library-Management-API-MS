package com.jphilips.library.bookinventory.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BranchCode {
    MNL_001("Manila National Library"),
    CVT_002("Cavite Library"),
    LGNA_003("Laguna Library");

    private final String name;
}
