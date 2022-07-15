package com.codecool.travelhelper.userPage.webclients;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MyFriendsImpl {

    @Autowired
    private UserRepository userRepository;

    public List<MyUserTable> getAllUsersByName(String nameForSearch){
        Optional<List<MyUserTable>> response = userRepository.findAllByFullNameContaining(nameForSearch);

        if (response.isPresent()){
            return response.get();
        }
        return null;
    }
}
