package com.codecool.travelhelper.login_registration_logout.controlers;


import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private LoginImpl loginImpl;


    @GetMapping("/login")
    public Long sendingTheUserIDSessionToFront(){
       return loginImpl.getCurrentUserId();
    }

    @PostMapping("/login_with_google")
    public Map<String,String> signWithGoogle(@RequestBody String data) {
        return loginImpl.signWithGoogle(data);
    }
}
