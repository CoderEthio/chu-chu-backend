package com.chu.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/1.0/")
public class FileUploadController {
    @Autowired
    FileService fileService;

    @PostMapping("/hoaxes/upload")
    FileAttachemment uploadFileForHoax(MultipartFile file) throws IOException {
        return fileService.saveAttachment(file);
    }

}
