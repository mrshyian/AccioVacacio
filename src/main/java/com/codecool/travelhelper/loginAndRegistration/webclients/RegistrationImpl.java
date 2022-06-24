package com.codecool.travelhelper.loginAndRegistration.webclients;


import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationImpl {

    @Autowired
    UserRepository userRepository;

    public void saveNewUserToDB(String fullName, String nickName, String birthday, String eMail, String password){
        userRepository.save(
                new MyUserTable(
                        fullName,
                        nickName,
                        birthday,
                        eMail,
                        password
                )
        );
    }
}
