package com.codecool.travelhelper.userPage.webclients;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPageImpl {

    @Autowired
    LoginImpl loginImpl;

    @Autowired
    UserRepository userRepository;

    public MyUserTable getUserFromDB(){
        Long userId = loginImpl.getCurrentUserId();
        return  userRepository.findMyUserTableById(userId);
    }
}
