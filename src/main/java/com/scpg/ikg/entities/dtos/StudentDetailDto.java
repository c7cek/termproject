package com.scpg.ikg.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetailDto {
    private int id;
    private String name;
    private String lastName;
}
