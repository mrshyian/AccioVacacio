package com.codecool.travelhelper.login_registration_logout.controlers;


import com.codecool.travelhelper.login_registration_logout.webclients.LogoutImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class LogOutConyroller {

    @Autowired
    private LogoutImpl logoutimpl;


    @PostMapping("/logout")
    public void logout(){
      logoutimpl.logoutSession();
    }
}
