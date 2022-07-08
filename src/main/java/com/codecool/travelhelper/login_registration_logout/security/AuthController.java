package com.codecool.travelhelper.login_registration_logout.security;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;

    public ResponseEntity<List<MyUserTable>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

}
