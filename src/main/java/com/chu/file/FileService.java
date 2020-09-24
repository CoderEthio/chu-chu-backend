package com.chu.file;

import com.chu.configuration.AppConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@EnableScheduling
public class FileService {
    final
    AppConfiguration appConfiguration;

    Tika tika;
    final
    FileAttachmentRepository fileAttachmentRepository;

    public FileService(AppConfiguration appConfiguration, FileAttachmentRepository fileAttachmentRepository) {
        super();
        this.appConfiguration = appConfiguration;
        this.tika=new Tika();
        this.fileAttachmentRepository = fileAttachmentRepository;
    }
    private String getRandomName() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    public String saveProfileImage(String base64Image) throws IOException {

        String imageName = getRandomName();
        byte[] decodedBytes = Base64.getDecoder().decode(base64Image);
        File target = new File(appConfiguration.getFullProfileImagesPath() + "/" + imageName);
        FileUtils.writeByteArrayToFile(target,decodedBytes);
        return imageName;

    }



    public String detectFileType(byte[] fileArr){
        //get the file and return the file type
        return tika.detect(fileArr);
    }

    public void deleteProfileImage(String image) {
        try {
            Files.deleteIfExists(Paths.get(appConfiguration.getFullProfileImagesPath()+"/"+image));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public FileAttachemment saveAttachment(MultipartFile file) throws IOException {
        FileAttachemment fileAttachemment = new FileAttachemment();
        fileAttachemment.setDate(new Date());
        String randomFileName = getRandomName();
        fileAttachemment.setFileName(randomFileName);

        //save the file to folder
        File target = new File(appConfiguration.getFullAttachmentPath()+"/"+randomFileName);
        byte[] fileAsByte = file.getBytes();
        FileUtils.writeByteArrayToFile(target,fileAsByte);
        fileAttachemment.setFileType(detectFileType(fileAsByte));

        return fileAttachmentRepository.save(fileAttachemment);
    }
    @Scheduled(fixedRate = 60*60*1000)
    public void cleanUpStorage(){
        Date oneHourAgo = new Date(System.currentTimeMillis()-(60*60*1000));
        List<FileAttachemment> oldFiles = fileAttachmentRepository.findByDateBeforeAndHoaxIsNotNull(oneHourAgo);
        for(FileAttachemment file: oldFiles){
            deleteAttachmentImage(file.getFileName());
            fileAttachmentRepository.deleteById(file.getId());
        }
    }

    public void deleteAttachmentImage(String image) {
        try {
            Files.deleteIfExists(Paths.get(appConfiguration.getFullAttachmentPath()+"/"+image));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
