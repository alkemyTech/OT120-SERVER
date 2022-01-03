package com.alkemy.ong.service;

import com.alkemy.ong.config.comm.AmazonS3Config;
import com.alkemy.ong.service.abstraction.IUploadImageService;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Service
public class UploadImageServiceImpl implements IUploadImageService {

    @Autowired
    private AmazonS3Config amazonS3Config;

    public String uploadImage(MultipartFile file) throws IOException, SdkClientException {

        byte[] bytes = file.getBytes();
        String fileObjKeyName = generateFileName(file);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        InputStream inputStream = new ByteArrayInputStream(bytes);

        try{
            PutObjectRequest request = new PutObjectRequest(amazonS3Config.getBucketName(), fileObjKeyName, inputStream, metadata);
            amazonS3Config.initialize().putObject(request);
        }catch(AmazonServiceException e){
            e.printStackTrace();
        }

        return amazonS3Config.getEndpointUrl()+fileObjKeyName;
    }

    private String generateFileName(MultipartFile file) {
        return new Date().getTime() + "-" + file.getOriginalFilename().replace(" ", "_");
    }
}
