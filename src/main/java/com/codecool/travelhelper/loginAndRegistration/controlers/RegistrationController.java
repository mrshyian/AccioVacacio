package com.codecool.travelhelper.loginAndRegistration.controlers;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.loginAndRegistration.webclients.RegistrationImpl;
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
public class RegistrationController {

    @Autowired
    RegistrationImpl registration;



    @PostMapping("/registration")
    public void registration(@RequestBody String data){
        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject)jsonParser.parse(data);
        String fullName = commentJsonObject.get("fullName").getAsString();
        String nickName = commentJsonObject.get("nickName").getAsString();
        String birthday = commentJsonObject.get("birthday").getAsString();
        String email = commentJsonObject.get("email").getAsString();
        String password = commentJsonObject.get("password").getAsString();


        registration.saveNewUserToDB(fullName, nickName, birthday, email, password);
    }
}
