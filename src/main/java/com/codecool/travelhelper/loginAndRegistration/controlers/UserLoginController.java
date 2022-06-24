package com.codecool.travelhelper.loginAndRegistration.controlers;


import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.loginAndRegistration.webclients.LoginImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserLoginController {

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
}
