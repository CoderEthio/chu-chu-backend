package com.chu.user;


import com.chu.error.NoFoundException;
import com.chu.file.FileService;
import com.chu.user.vm.UserUpdateVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {
    final
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;
    FileService fileService;

    public UserService(UserRepository userRepository,PasswordEncoder passwordEncoder,FileService fileService) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
        this.fileService=fileService;
    }

    public UserModel save(UserModel userModel){

        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userRepository.save(userModel);
    }

    public Page<UserModel> getUsers(UserModel loggedUser, Pageable page) {
        if(loggedUser!= null){
            return userRepository.findByUsernameNot(loggedUser.getUsername(),page);
        }
        return userRepository.findAll(page);
    }

    public UserModel getUserByUserName(String username) {
        UserModel inDB= userRepository.findByUsername(username);
        if(inDB ==null){
            throw new NoFoundException(username + "Not Found");
        }
        return inDB;
    }

    public UserModel update(long id, UserUpdateVM userUpdateVM) throws IOException {
        UserModel userInDB = userRepository.getOne(id);
        userInDB.setDisplayname(userUpdateVM.getDisplayname());
        if(userUpdateVM.getImage()!= null){
            String savedImageName = fileService.saveProfileImage(userUpdateVM.getImage());
            fileService.deleteProfileImage(userInDB.getImage());
            userInDB.setImage(savedImageName);
        }
        return userRepository.save(userInDB);
    }
}
