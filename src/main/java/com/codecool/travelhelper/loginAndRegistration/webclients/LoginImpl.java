package com.codecool.travelhelper.loginAndRegistration.webclients;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component

public class LoginImpl {

    @Autowired
    HttpSession session;

    @Autowired
    UserRepository userRepository;

    public void findUser(String email, String password) {
        MyUserTable userObject = userRepository.findAllByUserEMail(email);
        String passwordFromDB = userObject.getPassword();

        if(validationPassword(password,passwordFromDB)){
            session.setAttribute("userId", userObject.getId());
        }else {
            System.out.println("ujuw sto");
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
