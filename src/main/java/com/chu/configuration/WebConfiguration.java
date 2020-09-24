package com.chu.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Autowired
    AppConfiguration appConfiguration;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:"+appConfiguration.getUploadPath()+"/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));
    }

    @Bean
    CommandLineRunner createUploadFloder(){
        return (args) -> {
            createNonExistingFolder(appConfiguration.getUploadPath());
            createNonExistingFolder(appConfiguration.getFullProfileImagesPath());
            createNonExistingFolder(appConfiguration.getFullAttachmentPath());

        };
    }

    private void createNonExistingFolder(String path) {
        File uploadFolder  = new File(path) ;
        boolean uploadFolderExist = uploadFolder.exists() && uploadFolder.isDirectory();
        if(!uploadFolderExist){
            uploadFolder.mkdir();
        }
    }

}
