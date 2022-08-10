package com.codecool.travelhelper.API.simple.controllers;


import com.codecool.travelhelper.API.simple.models.AirportsIataCodeModel;
import com.codecool.travelhelper.API.simple.services.AirportsIataCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AirportsIataCodeController {
    @Autowired
    private final AirportsIataCodeService airportsIataCodeService;

    @GetMapping("/airport-iata-code/{cityName}/{countryIsoCode}")
    public AirportsIataCodeModel getAirportIataCode(@PathVariable String cityName, @PathVariable String countryIsoCode) {
        return airportsIataCodeService.getAirportIataCode(cityName, countryIsoCode );
    }
}

