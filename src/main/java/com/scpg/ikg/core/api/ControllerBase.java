package com.scpg.ikg.core.api;

import com.scpg.ikg.core.entities.IEntity;
import com.scpg.ikg.core.utilities.business.IServiceBase;
import com.scpg.ikg.core.utilities.results.DataResult;
import com.scpg.ikg.core.utilities.results.ErrorResult;
import com.scpg.ikg.core.utilities.results.IResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
public class ControllerBase<T extends IEntity, ServiceBase extends IServiceBase<T>> {

    private final ServiceBase serviceBase;

    @GetMapping("/getall")
    ResponseEntity<?> getall() {
        DataResult<List<T>> result = serviceBase.getAll();
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getbyid")
    ResponseEntity<?> getById(@RequestParam int id) {
        DataResult<T> result = serviceBase.getById(id);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    ResponseEntity<?> add(@RequestBody @Valid T t) {
        IResult result = serviceBase.add(t);

        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    ResponseEntity<?> delete(@RequestBody T t) {
        IResult result = serviceBase.delete(t);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    ResponseEntity<?> update(@RequestBody @Valid T t) {
        IResult result = serviceBase.update(t);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
