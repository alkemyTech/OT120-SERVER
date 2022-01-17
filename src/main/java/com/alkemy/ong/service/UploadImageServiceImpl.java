package com.alkemy.ong.service;

import com.alkemy.ong.config.comm.AmazonS3Config;
import com.alkemy.ong.dto.Base64MultipartFileDto;
import com.alkemy.ong.service.abstraction.IUploadImageService;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;

@Service
public class UploadImageServiceImpl implements IUploadImageService {

    @Autowired
    private AmazonS3Config amazonS3Config;

    public String uploadImage(Base64MultipartFileDto file) throws IOException, SdkClientException {
        String fileObjKeyName = "";

        try {
            File file1 = new File(file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(file1);
            fos.write(file.getBytes());
            fos.close();

            fileObjKeyName = generateFileName(file);

            PutObjectRequest request = new PutObjectRequest(amazonS3Config.getBucketName(), fileObjKeyName, file1);
            amazonS3Config.initialize().putObject(request);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }

        return amazonS3Config.getEndpointUrl()+fileObjKeyName;
    }

    private String generateFileName(MultipartFile file) {
        return new Date().getTime() + "-" + file.getOriginalFilename().replace(" ", "_");
    }
}
