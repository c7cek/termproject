package com.scpg.ikg.api.controller;

import com.scpg.ikg.business.abstracts.ISolutionService;
import com.scpg.ikg.core.api.ControllerBase;
import com.scpg.ikg.core.utilities.results.DataResult;
import com.scpg.ikg.core.utilities.results.ErrorResult;
import com.scpg.ikg.entities.concretes.Solution;
import com.scpg.ikg.entities.dtos.SolutionDetailDto;
import com.scpg.ikg.entities.dtos.SolutionUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/solutions")
public class SolutionController extends ControllerBase<Solution, ISolutionService> {

    ISolutionService iSolutionService;

    public SolutionController(ISolutionService iSolutionService) {
        super(iSolutionService);
        this.iSolutionService = iSolutionService;
    }

    @GetMapping("/getallsolutiondetail")
    ResponseEntity<?> getAllSolutionDetail() {
        DataResult<List<SolutionDetailDto>> result = iSolutionService.getAllSolutionDetail();
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getallsolutionbyhomework")
    ResponseEntity<?> getAllSolutionDetailByHomework(@RequestParam int homeworkId) {
        DataResult<List<SolutionDetailDto>> result = iSolutionService.getSolutionDetailDtoByHomework(homeworkId);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getsolutionupdatedtobyid")
    ResponseEntity<?> getSolutionUpdateDtoById(@RequestParam int solutionId) {
        DataResult<SolutionUpdateDto> result = iSolutionService.getSolutionUpdateDtoById(solutionId);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
