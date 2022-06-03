package com.codecool.travelhelper.API.rapidapi.controller.apicontroller;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.AirportServiceRapidApi;
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
    AirportServiceRapidApi airportServiceRapidApi;

    @GetMapping("/airport/{cityName}")
    public AirportDtoRapidApi getAirport(@PathVariable String cityName){
        return airportServiceRapidApi.getAirport(cityName);
    }

}
