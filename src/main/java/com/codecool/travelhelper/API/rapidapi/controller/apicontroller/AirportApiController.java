package com.codecool.travelhelper.API.rapidapi.controller.apicontroller;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportApiModel;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.AirportService;
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
    AirportService airportService;

    @GetMapping("/airport/{cityName}/{countryName}")
    public AirportApiModel getAirport(@PathVariable String cityName, @PathVariable String countryName){
        return airportService.getAirport(cityName, countryName);
    }

}
