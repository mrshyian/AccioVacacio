package com.codecool.travelhelper.login_registration_logout.controlers;

import com.codecool.travelhelper.login_registration_logout.webclients.RegistrationImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class RegistrationController {

    @Autowired
    private RegistrationImpl registrationImpl;


    @PostMapping("/registration")
    public String registration(@RequestBody String data){
        return registrationImpl.saveNewUserToDB(data);
    }
}
