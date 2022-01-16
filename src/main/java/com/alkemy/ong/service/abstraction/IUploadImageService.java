package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.Base64MultipartFileDto;
import com.amazonaws.SdkClientException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploadImageService {
    String uploadImage(Base64MultipartFileDto file) throws IOException, SdkClientException;
}
