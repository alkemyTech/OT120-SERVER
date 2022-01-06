package com.alkemy.ong.service.abstraction;

import com.amazonaws.SdkClientException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadImageService {
    String uploadImage(MultipartFile file) throws IOException, SdkClientException;
}
