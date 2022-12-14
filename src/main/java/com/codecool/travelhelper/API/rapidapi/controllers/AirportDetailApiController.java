package com.codecool.travelhelper.API.rapidapi.controllers;

import com.codecool.travelhelper.API.rapidapi.models.AirportDetailApiModel;
import com.codecool.travelhelper.API.rapidapi.services.AirportDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AirportDetailApiController {

    @Autowired
    private AirportDetailService airportDetailService;

    @GetMapping("/airport-details/{airportCode}/{cityName}/{countryName}")
    public AirportDetailApiModel getAirportDetail(@PathVariable String airportCode, @PathVariable String cityName, @PathVariable String countryName){
        return airportDetailService.getAirportDetail(airportCode, cityName, countryName);
    }
}
