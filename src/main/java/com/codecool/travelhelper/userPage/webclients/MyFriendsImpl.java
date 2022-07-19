package com.codecool.travelhelper.userPage.webclients;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.login_registration_logout.utils.sendMail.KindOfEmail;
import com.codecool.travelhelper.login_registration_logout.utils.sendMail.SendMailToUser;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MyFriendsImpl {
    @Autowired
    private SendMailToUser sendMailToUser;

    @Autowired
    private LoginImpl loginImpl;

    @Autowired
    private UserRepository userRepository;

    public List<MyUserTable> getAllUsersByName(String nameForSearch){
        Optional<List<MyUserTable>> response = userRepository.findAllByFullNameContaining(nameForSearch);

        if (response.isPresent()){
            return response.get();
        }
        return null;
    }

    public List<MyUserTable> getAllFriendsByMyUserId(String myUserId){
        Long userId = Long.parseLong(myUserId);
        return userRepository.findAllById(userId).getFriends();
    }

    public void addFriend(String friendIdString){
        Long friendId = Long.parseLong(friendIdString);
        MyUserTable newFriend = userRepository.findMyUserTableById(friendId);
        MyUserTable myUser = userRepository.findMyUserTableById(loginImpl.getCurrentUserId());
        myUser.addFriend(newFriend);
        userRepository.save(myUser);
        sendMailToUser.sendSimpleEmail(newFriend.getUserEMail(), newFriend.getFullName(), KindOfEmail.WAS_ADDED_TO_FRIENDS, myUser.getFullName());
    }

    public void removeFriend(String friendIdString){
        Long friendId = Long.parseLong(friendIdString);
        MyUserTable friendToRemove = userRepository.findMyUserTableById(friendId);
        MyUserTable myUser = userRepository.findAllById(loginImpl.getCurrentUserId());
        myUser.getFriends().remove(friendToRemove);
        userRepository.save(myUser);
        sendMailToUser.sendSimpleEmail(friendToRemove.getUserEMail(), friendToRemove.getFullName(), KindOfEmail.WAS_REMOVED_FROM_FRIENDS, myUser.getFullName());
    }


}
