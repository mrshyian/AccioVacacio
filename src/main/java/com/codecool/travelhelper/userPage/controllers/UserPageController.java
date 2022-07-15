package com.codecool.travelhelper.userPage.controllers;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.userPage.webclients.UserPageImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserPageController {

    @Autowired
    private UserPageImpl userPageImpl;

    @GetMapping("/usermainbar")
    public MyUserTable getUser() {
        return userPageImpl.getUserFromDB();
    }

    @PostMapping("/usermainbar")
    public void updateUser(@RequestBody String userData) {
        userPageImpl.updateUser(userData);
    }
}
