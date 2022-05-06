package com.scpg.ikg.api.controller;

import com.scpg.ikg.business.abstracts.IHomeworkService;
import com.scpg.ikg.core.api.ControllerBase;
import com.scpg.ikg.core.utilities.results.DataResult;
import com.scpg.ikg.core.utilities.results.ErrorResult;
import com.scpg.ikg.core.utilities.results.IResult;
import com.scpg.ikg.entities.concretes.Homework;
import com.scpg.ikg.entities.concretes.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/homeworks")
@RestController
public class HomeworkController extends ControllerBase<Homework, IHomeworkService> {

    private final IHomeworkService iHomeworkService;

    public HomeworkController(IHomeworkService iHomeworkService) {
        super(iHomeworkService);
        this.iHomeworkService = iHomeworkService;
    }

    @PostMapping("/addhomeworktouser")
    ResponseEntity<?> addHomeworkToUser(@RequestParam int homeworkId, @RequestParam int userId) {
        IResult result = iHomeworkService.addHomeworkToUser(homeworkId, userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getallbystudents")
    DataResult<List<Homework>> getAllByStudents(@RequestParam String username) {
        return iHomeworkService.getAllByStudents(username);
    }
}
