package com.codecool.travelhelper.login_registration_logout.webclients;


import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.login_registration_logout.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationImpl {

    @Autowired
    Util util;

    @Autowired
    UserRepository userRepository;

    public void saveNewUserToDB(String fullName, String nickName, String birthday, String eMail, String password){
        userRepository.save(
                new MyUserTable(
                        fullName,
                        nickName,
                        birthday,
                        eMail,
                        util.hashPassword(password)
                )
        );
    }

}
