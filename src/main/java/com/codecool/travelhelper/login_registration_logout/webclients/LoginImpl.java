package com.codecool.travelhelper.login_registration_logout.webclients;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.login_registration_logout.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component

public class LoginImpl {

    @Autowired
    Util util;

    @Autowired
    HttpSession session;

    @Autowired
    UserRepository userRepository;

    public void findUser(String email, String password) {
        MyUserTable userObject = userRepository.findAllByUserEMail(email);
        String passwordFromDB = userObject.getPassword();

        if(validationPassword(util.hashPassword(password),passwordFromDB)){
            session.setAttribute("userId", userObject.getId());
            System.out.println("Successful login");
        }else {
            System.out.println("incorrect login");
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
