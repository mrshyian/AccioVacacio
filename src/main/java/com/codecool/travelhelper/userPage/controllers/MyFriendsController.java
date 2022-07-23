package com.codecool.travelhelper.userPage.controllers;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.userPage.webclients.MyFriendsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class MyFriendsController {

    @Autowired
    private MyFriendsImpl myFriendsImpl;

    @GetMapping("/search_friend/{nameForSearch}")
    public List<MyUserTable> getAllUsersByName(@PathVariable String nameForSearch){
        return myFriendsImpl.getAllUsersByName(nameForSearch);
    }

    @GetMapping("/search_friend/id/{myUserId}")
    public List<MyUserTable> getAllFriendsByMyUserId(@PathVariable String myUserId){
        return myFriendsImpl.getAllFriendsByMyUserId(myUserId);
    }

    @GetMapping("/add_friend/{myUserId}")
    public void addFriend(@PathVariable String myUserId){
        myFriendsImpl.addFriend(myUserId);
    }

    @GetMapping("/remove_friend/{myUserId}")
    public void removeFriend(@PathVariable String myUserId){
        myFriendsImpl.removeFriend(myUserId);
    }
}
