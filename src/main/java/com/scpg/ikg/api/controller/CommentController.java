package com.scpg.ikg.api.controller;

import com.scpg.ikg.business.abstracts.ICommentService;
import com.scpg.ikg.core.api.ControllerBase;
import com.scpg.ikg.core.utilities.results.DataResult;
import com.scpg.ikg.core.utilities.results.ErrorResult;
import com.scpg.ikg.core.utilities.results.IResult;
import com.scpg.ikg.entities.concretes.Comment;
import com.scpg.ikg.entities.dtos.CommentDetailDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
public class CommentController extends ControllerBase<Comment, ICommentService> {

    ICommentService iCommentService;

    public CommentController(ICommentService iCommentService) {
        super(iCommentService);
        this.iCommentService = iCommentService;
    }

//     @PostMapping("/addcommenttohomework")
//    ResponseEntity<?> addCommentToHomework(@RequestParam int commentId, int homeworkId){
//        var result = iCommentService.addCommentToHomework(commentId,homeworkId);
//        if (result.isSuccess()) {
//            return new ResponseEntity<>(result, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new ErrorResult(result.getMessage()),HttpStatus.BAD_REQUEST);
//     }

    @PostMapping("/addcommenttosolution")
    ResponseEntity<?> addCommentToSolution(@RequestParam int commentId, int solutionId) {
        IResult result = iCommentService.addCommentToSolution(commentId, solutionId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addcommenttouser")
    ResponseEntity<?> addCommentToUser(@RequestParam int commentId, int userId) {
        IResult result = iCommentService.addCommentToUser(commentId, userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/getcommentdetaildtobyhomework")
    ResponseEntity<?> getAllCommentDtoByHomeworkId(@RequestParam int homeworkId) {
        DataResult<List<CommentDetailDto>> result = iCommentService.getAllCommentDtoByHomeworkId(homeworkId);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
