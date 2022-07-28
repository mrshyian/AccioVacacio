package com.codecool.travelhelper.login_registration_logout.webclients;


import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.login_registration_logout.controlers.RegistrationController;
import com.codecool.travelhelper.login_registration_logout.utils.sendMail.KindOfEmail;
import com.codecool.travelhelper.login_registration_logout.utils.sendMail.SendMailToUser;
import com.codecool.travelhelper.login_registration_logout.utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegistrationImpl {
    @Autowired
    private SendMailToUser sendMailToUser;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String saveNewUserToDB(String data) {

        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject) jsonParser.parse(data);
        String fullName = commentJsonObject.get("fullName").getAsString();
        String nickName = commentJsonObject.get("nickName").getAsString();
        String birthday = commentJsonObject.get("birthday").getAsString();
        String eMail = commentJsonObject.get("email").getAsString();
        String password = commentJsonObject.get("password").getAsString();

        Optional<MyUserTable> userFromDB = userRepository.findAllByUserEMail(eMail);
        if (userFromDB.isPresent()) {
            System.out.println("this email is already taken");
            return "This email is already taken";
        } else {
            userRepository.save(
                    new MyUserTable(
                            fullName,
                            nickName,
                            birthday,
                            eMail,
                            passwordEncoder.encode(password)
                    )
            );
            sendMailToUser.sendSimpleEmail(eMail, fullName, KindOfEmail.AFTER_REGISTRATION, null);
            return null;
        }
    }
}
