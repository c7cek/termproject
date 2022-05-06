package com.scpg.ikg.business.abstracts;

import com.scpg.ikg.core.utilities.business.IServiceBase;
import com.scpg.ikg.core.utilities.results.DataResult;
import com.scpg.ikg.core.utilities.results.IResult;
import com.scpg.ikg.entities.concretes.Comment;
import com.scpg.ikg.entities.dtos.CommentDetailDto;

import java.util.List;

public interface ICommentService extends IServiceBase<Comment> {

    IResult addCommentToUser(int commentId, int userId);

    IResult addCommentToSolution(int commentId, int solutionId);

    DataResult<List<CommentDetailDto>> getAllCommentDtoByHomeworkId(int homeworkId);

}
