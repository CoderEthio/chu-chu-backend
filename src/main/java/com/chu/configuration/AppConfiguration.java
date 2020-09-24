package com.chu.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "chuna")
@Data
public class AppConfiguration {
    String uploadPath;
    String profileImagesFolder = "profile";
    String attachmentsFolder = "attachment";

    public String getFullProfileImagesPath(){
        return this.uploadPath + "/"+ this.profileImagesFolder;
    }
    public String getFullAttachmentPath(){
        return this.uploadPath + "/"+ this.attachmentsFolder;
    }
}
