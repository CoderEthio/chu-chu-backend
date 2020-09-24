package com.chu.shared;

import com.chu.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class ProfileImageValidator implements ConstraintValidator<ProfileImage,String> {

    @Autowired
    FileService fileService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }
        byte[] decoderBytes = Base64.getDecoder().decode(value);
        String fileType = fileService.detectFileType(decoderBytes);
        if(fileType.equalsIgnoreCase("image/png") || fileType.equalsIgnoreCase("image/jpg")){
            return true;
        }
        return false;
    }

}
