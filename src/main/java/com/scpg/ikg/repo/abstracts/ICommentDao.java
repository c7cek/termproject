package com.scpg.ikg.repo.abstracts;

import com.scpg.ikg.entities.concretes.Comment;
import com.scpg.ikg.entities.dtos.CommentDetailDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICommentDao extends JpaRepository<Comment, Integer> {

    Comment findById(int commentId);

    @Query("select new com.scpg.ikg.entities.dtos.CommentDetailDto(c.id,u.id,h.id,s.id,u.name,u.lastName,u.username,c.text,c.date)" +
            " from Comment c" +
            " inner join c.student u" +
            " inner join c.homework h" +
            " inner join c.solution s" +
            " where h.id=:homeworkId order by c.id asc")
    List<CommentDetailDto> getAllCommentDtoByHomeworkId(int homeworkId);
}
