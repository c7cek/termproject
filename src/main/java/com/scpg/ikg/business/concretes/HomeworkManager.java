package com.scpg.ikg.business.concretes;

import com.scpg.ikg.business.abstracts.IHomeworkService;
import com.scpg.ikg.business.abstracts.IStudentService;
import com.scpg.ikg.business.tools.Messages;
import com.scpg.ikg.core.utilities.business.BusinessRule;
import com.scpg.ikg.core.utilities.results.*;
import com.scpg.ikg.entities.concretes.Homework;
import com.scpg.ikg.entities.concretes.Student;
import com.scpg.ikg.repo.abstracts.IHomeworkDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeworkManager implements IHomeworkService {
    private final IHomeworkDao iHomeworkDao;
    private final IStudentService iStudentService;


    @Override
    public IResult addHomeworkToUser(int homeworkId, int studentId) {
        IResult result = BusinessRule.run(isHomeworkExistById(homeworkId), isHomeworkExistById(homeworkId));
        if (result != null)
            return result;
        Homework homework = this.iHomeworkDao.findById(homeworkId);
        Student student = this.iStudentService.getById(studentId).getData();
        student.getHomeworks().add(homework);
        iStudentService.update(student);
        return new SuccessResult(Messages.homeworkAddedToUser);

    }

    @Override
    public DataResult<List<Homework>> getAll() {
        return new SuccesDataResult(iHomeworkDao.findAll(), Messages.homeworkListed);
    }

    @Override
    public IResult add(Homework homework) {
        iHomeworkDao.save(homework);
        return new SuccessResult(Messages.homeworkSave);
    }

    @Override
    public IResult update(Homework homework) {
        iHomeworkDao.save(homework);
        return new SuccessResult(Messages.homeworkUpdate);
    }

    @Override
    public IResult delete(Homework homework) {
        iHomeworkDao.delete(homework);
        return new SuccessResult(Messages.homeworkDelete);
    }

    @Override
    public DataResult<Homework> getById(Integer id) {
        boolean result = iHomeworkDao.existsById(id);
        if (result)
            return new SuccesDataResult<>(iHomeworkDao.getById(id));
        return new ErrorDataResult<>(null);
    }

    public DataResult<List<Homework>> getAllByStudents(String username) {
        return new SuccesDataResult<>(iHomeworkDao.getAllByStudents_Username(username));
    }

    private IResult isHomeworkExistById(int homeworkId) {
        boolean result = iHomeworkDao.existsById(homeworkId);
        if (!result)
            return new ErrorResult(Messages.homeworkNotFound);
        return new SuccessResult();
    }


}
