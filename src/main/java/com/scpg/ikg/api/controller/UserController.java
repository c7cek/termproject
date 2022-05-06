package com.scpg.ikg.api.controller;

import com.scpg.ikg.business.abstracts.IUserService;
import com.scpg.ikg.core.api.ControllerBase;
import com.scpg.ikg.core.entities.User;
import com.scpg.ikg.core.utilities.results.ErrorResult;
import com.scpg.ikg.core.utilities.results.IResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class
UserController extends ControllerBase<User, IUserService> {

    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        super(iUserService);
        this.iUserService = iUserService;
    }


    @PostMapping("/addroletouser")
    public ResponseEntity<IResult> addRoleToUser(@RequestParam String username, @RequestParam String roleName) {
        IResult result = iUserService.addRoleToUser(username, roleName);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
