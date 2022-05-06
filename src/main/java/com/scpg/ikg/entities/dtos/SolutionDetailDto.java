package com.scpg.ikg.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolutionDetailDto {
    private int id;
    private String description;
    private int studentId;
    private String studentUsername;
    private String studentName;
    private String studentLastName;
    private Date date;
    private int homeworkId;
}
