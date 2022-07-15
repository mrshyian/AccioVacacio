package com.codecool.travelhelper.API.rapidapi.controllers;

import com.codecool.travelhelper.API.rapidapi.models.GetCoordinatesModel;
import com.codecool.travelhelper.API.rapidapi.services.GetCoordinatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class GetCoordinatesController{

    @Autowired
    private GetCoordinatesService getCoordinatesService;

    @GetMapping("/get_coordinates/{cityName}")
    public GetCoordinatesModel getWeather(@PathVariable String cityName){
        return getCoordinatesService.getCoordinates(cityName);
    }
}
