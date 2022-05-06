package com.scpg.ikg.business.abstracts;

import com.scpg.ikg.core.utilities.business.IServiceBase;
import com.scpg.ikg.core.utilities.results.DataResult;
import com.scpg.ikg.core.utilities.results.IResult;
import com.scpg.ikg.entities.concretes.Solution;
import com.scpg.ikg.entities.dtos.SolutionDetailDto;
import com.scpg.ikg.entities.dtos.SolutionUpdateDto;

import java.util.List;

public interface ISolutionService extends IServiceBase<Solution> {
    DataResult<Solution> getById(Integer id);
    IResult existBySolutionId(int solutionId);

    DataResult<List<SolutionDetailDto>> getAllSolutionDetail();
    DataResult<List<SolutionDetailDto>> getSolutionDetailDtoByHomework(int homeworkId);
    DataResult<SolutionUpdateDto> getSolutionUpdateDtoById(int solutionId);
}
