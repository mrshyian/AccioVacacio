package com.codecool.travelhelper.API.rapidapi.apicontroller;

import com.codecool.travelhelper.API.rapidapi.apiservice.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AirportApiController {

    @Autowired
    AirportService airportService;

//    @GetMapping("/airport/{cityName}/{countryName}")
//    public AirportApiModel getAirport(@PathVariable String cityName, @PathVariable String countryName){
//        return airportService.getAirport(cityName, countryName);
//    }

}
