package com.scpg.ikg.adapters.concretes;

import com.cloudinary.Cloudinary;
import com.scpg.ikg.adapters.abstracts.ICloudinaryAdapterService;
import com.scpg.ikg.business.tools.Messages;
import com.scpg.ikg.cloudinaryApi.CloudinaryApi;
import com.scpg.ikg.core.utilities.results.DataResult;
import com.scpg.ikg.core.utilities.results.ErrorDataResult;
import com.scpg.ikg.core.utilities.results.SuccesDataResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryAdapterManager implements ICloudinaryAdapterService {

    private final Cloudinary cloudinary;
    private final CloudinaryApi cloudinaryApi;

    public CloudinaryAdapterManager(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
        this.cloudinaryApi = new CloudinaryApi();
    }

    @Override
    public DataResult<Map> uploadImage(MultipartFile image) {
        Map result = cloudinaryApi.uploadImage(image, cloudinary);
        if (result != null || result.isEmpty())
            return new SuccesDataResult<>(result);
        return new ErrorDataResult<>(Messages.imageUploadError);
    }
}
