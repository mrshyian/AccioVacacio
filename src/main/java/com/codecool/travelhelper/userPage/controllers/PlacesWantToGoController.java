package com.codecool.travelhelper.userPage.controllers;

import com.codecool.travelhelper.userPage.models.PlaceWantToGoModel;
import com.codecool.travelhelper.userPage.webclients.PlacesWantToGoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PlacesWantToGoController {

    @Autowired
    PlacesWantToGoImpl placesWantToGoImpl;

    @PostMapping("/placewanttogo")
    public void setPlace(@RequestBody String countryAndCity) {
        placesWantToGoImpl.addNewPlace(countryAndCity);
    }

    @GetMapping("/placewanttogo")
    public List<PlaceWantToGoModel> getPlace() {
        return placesWantToGoImpl.getPlacesFromDB();
    }
}
