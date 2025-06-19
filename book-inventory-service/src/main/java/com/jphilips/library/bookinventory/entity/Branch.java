package com.jphilips.library.bookinventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "branches")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Branch {

    @Id
    private String code;

    private String name;
}
