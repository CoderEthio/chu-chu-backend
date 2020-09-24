package com.chu.user;

import com.chu.shared.CurrentUser;
import com.chu.user.vm.UserVM;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @PostMapping("/api/1.0/login")
    UserVM handleLogin(@CurrentUser UserModel loggedUser){

        return new UserVM(loggedUser);
    }

}
