package com.codecool.travelhelper.API.rapidapi.controllers;


import com.codecool.travelhelper.API.rapidapi.models.flights.AirportInfoApiModel;
import com.codecool.travelhelper.API.rapidapi.services.flights.AirportInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AirportApiController {

    @Autowired
    AirportInfoService airportInfoService;

    @GetMapping("/airport-info/{cityName}/{countryIsoCode}")
    public AirportInfoApiModel getAirportDetail(@PathVariable String cityName, @PathVariable String countryIsoCode){
        return airportInfoService.getAirportData(cityName, countryIsoCode);
    }

}
