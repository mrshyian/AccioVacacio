//package com.codecool.travelhelper.userPage.webclients;
//
//import com.codecool.travelhelper.aws.database.models.MyUserTable;
//import com.codecool.travelhelper.aws.database.models.PhotosFromTripsTable;
//import com.codecool.travelhelper.aws.database.repositories.PhotosFromTripsRepository;
//import com.codecool.travelhelper.aws.database.repositories.UserRepository;
//import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class PhotosFromTripsImpl {
//
//
//    @Autowired
//    LoginImpl loginImpl;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    PhotosFromTripsRepository photosFromTripsRepository;
//
//    public List<PhotosFromTripsTable> getPhotos(){
//        List<PhotosFromTripsTable> list = new ArrayList<>();
//        MyUserTable myUserTable = userRepository.findMyUserTableById(loginImpl.getCurrentUserId());
//        Optional<List<PhotosFromTripsTable>> listOfPhotos = photosFromTripsRepository.findAllByMyUserTableId(myUserTable.getId());
//        System.out.println(listOfPhotos);
//        if(listOfPhotos)
//        return listOfPhotos;
//    }
//}
