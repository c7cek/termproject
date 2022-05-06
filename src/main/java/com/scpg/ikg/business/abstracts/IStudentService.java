package com.scpg.ikg.business.abstracts;

import com.scpg.ikg.core.utilities.business.IServiceBase;
import com.scpg.ikg.core.utilities.results.DataResult;
import com.scpg.ikg.core.utilities.results.IResult;
import com.scpg.ikg.entities.concretes.Student;
import com.scpg.ikg.entities.dtos.StudentDetailDto;
import com.scpg.ikg.entities.dtos.StudentEditDto;

public interface IStudentService extends IServiceBase<Student> {
    DataResult<Student> getById(Integer id);
    IResult existById(int studentId);
    DataResult<StudentDetailDto> getStudentDetailDto(String username);
    DataResult<StudentEditDto> getStudentDtoByUsername(String username);


}
