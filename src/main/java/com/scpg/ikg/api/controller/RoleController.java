package com.scpg.ikg.api.controller;

import com.scpg.ikg.business.abstracts.IRoleService;
import com.scpg.ikg.core.api.ControllerBase;
import com.scpg.ikg.core.entities.Role;
import com.scpg.ikg.core.utilities.results.DataResult;
import com.scpg.ikg.core.utilities.results.ErrorResult;
import com.scpg.ikg.core.utilities.results.IResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/role")
public class RoleController extends ControllerBase<Role, IRoleService> {

    private final IRoleService iRoleService;

    public RoleController(IRoleService iRoleService) {
        super(iRoleService);
        this.iRoleService = iRoleService;
    }

    @GetMapping("getallbyuser")
    ResponseEntity<?> getAllByUser(@RequestParam String username) {
        DataResult<Role> result = iRoleService.getAllByUser(username);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("isadmin")
    ResponseEntity<?> isAdmin(@RequestParam String username) {
        IResult result = iRoleService.isAdmin(username);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
