package com.codecool.travelhelper.userPage.controllers;

import com.codecool.travelhelper.userPage.webclients.PlacesWantToGoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PlacesWantToGoController {

    @Autowired
    PlacesWantToGoImpl placesWantToGoImpl;

    @PostMapping("/placewanttogo")
    public void getPlace(@RequestBody String countryAndCity) {
        placesWantToGoImpl.addNewPlace(countryAndCity);
    }
}
