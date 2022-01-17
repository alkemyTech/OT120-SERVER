package com.alkemy.ong.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class Base64MultipartFileDto implements MultipartFile {

    private final byte[] imgContent;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public Base64MultipartFileDto(byte[] imgContent) {
        this.imgContent = imgContent;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getOriginalFilename() {
        return this.name;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
        new FileOutputStream(file).write(imgContent);

    }
}
