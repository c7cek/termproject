package com.scpg.ikg.business.abstracts;

import com.scpg.ikg.core.utilities.business.IServiceBase;
import com.scpg.ikg.core.utilities.results.DataResult;
import com.scpg.ikg.core.utilities.results.IResult;
import com.scpg.ikg.entities.concretes.Homework;
import com.scpg.ikg.entities.concretes.Student;

import java.util.List;

public interface IHomeworkService extends IServiceBase<Homework> {
    IResult addHomeworkToUser(int homeworkId, int studentId);

    DataResult<List<Homework>> getAllByStudents(String  username);


}
