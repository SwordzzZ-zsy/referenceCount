package com.learn.controller;

import com.learn.pojo.Result;
import com.learn.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public Result uploadEmp(MultipartFile file){
        boolean a=false;
        String fileName = file.getOriginalFilename();
        try {
             a=uploadService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(a);
    }


}
