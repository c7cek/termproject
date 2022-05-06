package com.scpg.ikg.api.controller;


import com.scpg.ikg.business.abstracts.IAuthService;
import com.scpg.ikg.business.abstracts.IResetTokenService;
import com.scpg.ikg.business.abstracts.IStudentService;
import com.scpg.ikg.core.utilities.results.ErrorResult;
import com.scpg.ikg.core.utilities.results.IResult;
import com.scpg.ikg.entities.concretes.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AuthController {
    private final IStudentService iStudentService;
    private final IAuthService iAuthService;
    private final IResetTokenService iResetTokenService;


    @PostMapping("/register")
    ResponseEntity<?> register(@Valid @RequestBody Student student) {
        IResult result = iStudentService.add(student);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/passwordreset")
    ResponseEntity<?> passwordReset(@RequestParam String username, @RequestParam String password, @RequestParam String newPassword) {
        IResult result = iAuthService.passwordReset(username, password, newPassword);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/passwordresetbyemail")
    ResponseEntity<?> passwordResetByEmail(@RequestParam String email) throws MessagingException {
        IResult result = iResetTokenService.sendPasswordResetLink(email);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/istokenvalid")
    ResponseEntity<?> isTokenValid(@RequestParam String token) {
        IResult result = iAuthService.isTokenExpired(token);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/passwordresetbytoken")
    ResponseEntity<?> passwordResetByToken(@RequestParam String token, @RequestParam String password) {
        IResult result = iAuthService.passwordResetWithToken(token, password);
        if (result.isSuccess())
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
