package com.chu.user;



import com.chu.error.ApiError;
import com.chu.shared.CurrentUser;
import com.chu.shared.GenericResponse;
import com.chu.user.vm.UserUpdateVM;
import com.chu.user.vm.UserVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/1.0")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/users")
    GenericResponse createUser(@Valid @RequestBody UserModel userModel){
        userService.save(userModel);
        return new GenericResponse("User Saved");
    }

    @GetMapping("/users")
    Page<UserVM> getUsers(@CurrentUser UserModel loggedUser, Pageable page){
        return userService.getUsers(loggedUser,page).map(UserVM::new);
    }

    @GetMapping("/users/{username}")
    UserVM getUserName(@PathVariable String username){
        UserModel user = userService.getUserByUserName(username);
        return new UserVM(user);
    }

    @PutMapping("/users/{id:[0-9]+}")
    @PreAuthorize("#id == principal.id")
    UserVM updateUser(@PathVariable long id,@Valid @RequestBody(required = false) UserUpdateVM userUpdateVM) throws IOException {
        UserModel user = userService.update(id,userUpdateVM);
        return new UserVM(user);
    }




}
