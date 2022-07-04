package com.codecool.travelhelper.login_registration_logout.webclients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class LogoutImpl {

    @Autowired
    HttpSession httpSession;
    public void logoutSession(){
        httpSession.removeAttribute("userId");
    }
}
