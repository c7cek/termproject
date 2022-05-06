package com.scpg.ikg.business.abstracts;

import com.scpg.ikg.core.utilities.business.IServiceBase;
import com.scpg.ikg.core.utilities.results.IResult;
import com.scpg.ikg.entities.concretes.Photo;
import org.springframework.web.multipart.MultipartFile;

public interface IPhotoService extends IServiceBase<Photo> {
    IResult addImage(MultipartFile image);
    IResult addPhotoToSolution(int photoId, int solutionId);
}
