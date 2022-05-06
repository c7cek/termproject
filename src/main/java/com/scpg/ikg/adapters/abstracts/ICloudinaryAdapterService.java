package com.scpg.ikg.adapters.abstracts;

import com.scpg.ikg.core.utilities.results.DataResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ICloudinaryAdapterService {
    DataResult<Map> uploadImage(MultipartFile image);
}