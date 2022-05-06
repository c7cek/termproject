package com.scpg.ikg.business.concretes;

import com.scpg.ikg.business.abstracts.*;
import com.scpg.ikg.business.tools.Messages;
import com.scpg.ikg.core.utilities.business.BusinessRule;
import com.scpg.ikg.core.utilities.results.*;
import com.scpg.ikg.entities.concretes.Comment;
import com.scpg.ikg.entities.concretes.Homework;
import com.scpg.ikg.entities.concretes.Solution;
import com.scpg.ikg.entities.concretes.Student;
import com.scpg.ikg.entities.dtos.CommentDetailDto;
import com.scpg.ikg.repo.abstracts.ICommentDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentManager implements ICommentService {
    private final ICommentDao iCommentDao;
    private final IUserService iUserService;
    private final IHomeworkService iHomeworkService;
    private final ISolutionService iSolutionService;
    private final IStudentService iStudentService;

    @Override
    public IResult addCommentToSolution(int commentId, int solutionId) {
        IResult result = BusinessRule.run(isCommentExistById(commentId), isSoluitonExistById(solutionId));

        if (result != null)
            return result;
        Comment comment = iCommentDao.getById(commentId);
        DataResult<Solution> solution = iSolutionService.getById(solutionId);

        comment.setSolution(solution.getData());
        iCommentDao.save(comment);
        return new SuccessResult();
    }

    @Override
    public DataResult<List<CommentDetailDto>> getAllCommentDtoByHomeworkId(int homeworkId) {
        return new SuccesDataResult<>(iCommentDao.getAllCommentDtoByHomeworkId(homeworkId));
    }


//    @Override
//    public IResult addCommentToHomework(int commentId, int homeworkId) {
//        var result = BusinessRule.run(isCommentExistById(commentId), isHomeworkExistById(homeworkId));
//
//        if (result != null)
//            return result;
//        var comment = iCommentDao.getById(commentId);
//        var homework = iHomeworkService.getById(homeworkId);
//
//        comment.setHomework(homework.getData());
//
//        iCommentDao.save(comment);
//        return new SuccessResult();
//    }

    @Override
    public IResult addCommentToUser(int commentId, int userId) {
        IResult result = BusinessRule.run(isCommentExistById(commentId), isExistUserById(userId));

        if (result != null)
            return result;
        Comment comment = iCommentDao.getById(commentId);
        DataResult<Student> student = iStudentService.getById(userId);
        comment.setStudent(student.getData());
        iCommentDao.save(comment);

        return new SuccessResult(Messages.commentAddedToUser);
    }


    @Override
    public DataResult<List<Comment>> getAll() {
        return new SuccesDataResult(iCommentDao.findAll(), Messages.commentListed);

    }

    @Override
    public IResult add(Comment comment) {
        comment.setDate(LocalDateTime.now());
        iCommentDao.save(comment);
        return new SuccessResult(Messages.commentSave);
    }

    @Override
    public IResult update(Comment comment) {
        iCommentDao.save(comment);
        return new SuccessResult(Messages.commentUpdate);

    }

    @Override
    public IResult delete(Comment comment) {
        iCommentDao.delete(comment);
        return new SuccessResult(Messages.commentDelete);
    }

    @Override
    public DataResult<Comment> getById(Integer id) {
        return new SuccesDataResult(iCommentDao.getById(id), Messages.commentListed);
    }

    private IResult isCommentExistById(int commentId) {
        boolean result = iCommentDao.existsById(commentId);
        if (!result)
            return new ErrorResult(Messages.commentNotFound);

        return new SuccessResult();
    }

    private IResult isExistUserById(int userId) {

        IResult result = iUserService.existsById(userId);
        if (!result.isSuccess())
            return new ErrorResult(Messages.userNotFound);
        return new SuccessResult();
    }

    private IResult isHomeworkExistById(int homeworkId) {
        DataResult<Homework> result = iHomeworkService.getById(homeworkId);
        if (result.getData() == null)
            return new ErrorResult(Messages.homeworkNotFound);
        return new SuccessResult();

    }

    private IResult isSoluitonExistById(int soluitonId) {
        DataResult<Solution> result = iSolutionService.getById(soluitonId);
        if (result.getData() == null)
            return new ErrorResult(Messages.solutionNotFound);
        return new SuccessResult();
    }
}
