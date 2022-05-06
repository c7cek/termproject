package com.scpg.ikg.business.concretes;

import com.scpg.ikg.business.abstracts.ISolutionService;
import com.scpg.ikg.business.tools.Messages;
import com.scpg.ikg.core.utilities.results.*;
import com.scpg.ikg.entities.concretes.Solution;
import com.scpg.ikg.entities.dtos.SolutionDetailDto;
import com.scpg.ikg.entities.dtos.SolutionUpdateDto;
import com.scpg.ikg.repo.abstracts.ISolutionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolutionManager implements ISolutionService {
    private final ISolutionDao iSolutionDao;

    @Override
    public DataResult<List<Solution>> getAll() {
        return new SuccesDataResult(iSolutionDao.findAll(), Messages.solutionsListed);
    }

    @Override
    public IResult add(Solution solution) {
        iSolutionDao.save(solution);
        return new SuccessResult(Messages.solutionAdd);
    }

    @Override
    public IResult update(Solution solution) {
        iSolutionDao.save(solution);
        return new SuccessResult(Messages.solutionUpdate);
    }

    @Override
    public IResult delete(Solution solution) {
        iSolutionDao.delete(solution);
        return new SuccessResult(Messages.solutionDelete);
    }

    @Override
    public DataResult<Solution> getById(Integer id) {
        return new SuccesDataResult<>(iSolutionDao.getById(id), Messages.soluitonListed);
    }

    @Override
    public IResult existBySolutionId(int solutionId) {
        if (iSolutionDao.existsById(solutionId))
            return new SuccessResult((Messages.solutionFound));
        return new ErrorResult(Messages.solutionNotFound);

    }

    @Override
    public DataResult<List<SolutionDetailDto>> getAllSolutionDetail() {
        return new SuccesDataResult<>(this.iSolutionDao.getAllSolutionDetail());
    }

    @Override
    public DataResult<List<SolutionDetailDto>> getSolutionDetailDtoByHomework(int homeworkId) {
        return new SuccesDataResult<>(this.iSolutionDao.getSolutionDetailDtoByHomework(homeworkId), Messages.soluitonListed);
    }

    @Override
    public DataResult<SolutionUpdateDto> getSolutionUpdateDtoById(int solutionId) {
        return new SuccesDataResult<>(iSolutionDao.getSolutionUpdateDtoById(solutionId), Messages.soluitonListed);
    }


}
