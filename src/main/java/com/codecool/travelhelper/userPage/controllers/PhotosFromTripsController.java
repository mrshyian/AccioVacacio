package com.codecool.travelhelper.userPage.controllers;

import com.codecool.travelhelper.aws.database.models.PhotosFromTripsTable;
import com.codecool.travelhelper.aws.database.repositories.PhotosFromTripsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PhotosFromTripsController {

    @Autowired
    PhotosFromTripsRepository photosFromTripsRepository;

    @GetMapping("/photos")
    List<PhotosFromTripsTable> getPhotos(){
        return photosFromTripsRepository.findAll();
    }
}
