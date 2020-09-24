package com.chu.user;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidation implements ConstraintValidator<UniqueUserName, String> {
    @Autowired
    UserRepository userRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        UserModel inDb = userRepository.findByUsername(value);
        if(inDb==null){
            return true;
        }
        return false;
    }
}
