package com.learn.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    boolean batchImport(String fileName, MultipartFile file) throws IOException, InvalidFormatException;
}
