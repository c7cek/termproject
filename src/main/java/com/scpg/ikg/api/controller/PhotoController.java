package com.scpg.ikg.api.controller;

import com.scpg.ikg.business.abstracts.IPhotoService;
import com.scpg.ikg.core.api.ControllerBase;
import com.scpg.ikg.core.utilities.results.ErrorResult;
import com.scpg.ikg.core.utilities.results.IResult;
import com.scpg.ikg.entities.concretes.Photo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/photos")
public class PhotoController extends ControllerBase<Photo, IPhotoService> {
    private final IPhotoService iPhotoService;

    public PhotoController(IPhotoService iPhotoService){
        super(iPhotoService);
        this.iPhotoService = iPhotoService;
    }

    @PostMapping("/addimage")
    public ResponseEntity<?> addImage(@RequestPart MultipartFile image){
        IResult result = iPhotoService.addImage(image);
        if (result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addphototosolution")
    ResponseEntity<?> addPhotoToSolution(@RequestParam int photoId, @RequestParam int solutionId){
        IResult result = iPhotoService.addPhotoToSolution(photoId, solutionId);
        if (result.isSuccess()){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ErrorResult(result.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
