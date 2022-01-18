package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.dto.Base64MultipartFileDto;
import com.amazonaws.SdkClientException;

import java.io.IOException;

public interface IUploadImageService {

    String uploadImage(Base64MultipartFileDto file) throws IOException, SdkClientException;
}
