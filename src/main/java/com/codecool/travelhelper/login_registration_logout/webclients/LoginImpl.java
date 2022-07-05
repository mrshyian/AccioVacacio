package com.codecool.travelhelper.login_registration_logout.webclients;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.login_registration_logout.utils.Util;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
@Getter
@Setter
public class LoginImpl {

    private Long currentUserId;

    @Autowired
    Util util;

    @Autowired
    HttpSession session;

    @Autowired
    UserRepository userRepository;

    public String findUser(String data) {

        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject)jsonParser.parse(data);
        String email = commentJsonObject.get("email").getAsString();
        String password = commentJsonObject.get("password").getAsString();

        Optional<MyUserTable> userObject = userRepository.findAllByUserEMail(email);
        this.setCurrentUserId(null);

        if (userObject.isPresent()){
            String passwordFromDB = userObject.get().getPassword();
            if(validationPassword(util.hashPassword(password),passwordFromDB)){
                session.setAttribute("userId", userObject.get().getId());
                this.setCurrentUserId(userObject.get().getId());
                return null;
            }else {
                System.out.println("incorrect log in ");
                return "Mail or password is incorrect";
            }
        } else {
            System.out.println("mail or password is incorrect");
            return "Mail or password is incorrect";
        }
    }

    public boolean validationPassword(String password, String passwordFromDB ){
        if (password.equals(passwordFromDB)){
            return true;
        }else {
            return false;
        }
    }

}
