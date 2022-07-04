package com.codecool.travelhelper.login_registration_logout.controlers;


import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoginController {

    @Autowired
    LoginImpl loginImpl;


    @PostMapping("/login")
    public void login(@RequestBody String data){
        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject)jsonParser.parse(data);
        String email = commentJsonObject.get("email").getAsString();
        String password = commentJsonObject.get("password").getAsString();
        loginImpl.findUser(email, password);
    }

    @GetMapping("/login")
    public Long sendingTheUserIDSessionToFront(){
       return loginImpl.getCurrentUserId();
    }
}
