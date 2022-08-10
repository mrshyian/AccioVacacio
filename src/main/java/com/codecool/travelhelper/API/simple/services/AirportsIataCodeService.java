package com.codecool.travelhelper.API.simple.services;


import com.codecool.travelhelper.API.simple.models.AirportsIataCodeModel;
import com.codecool.travelhelper.API.simple.webclient.AirportsIataCodeClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportsIataCodeService {
    @Autowired
    private final AirportsIataCodeClient airportsIataCodeClient;

    public AirportsIataCodeModel getAirportIataCode(String cityName, String countryIsoCode){
        return airportsIataCodeClient.getAirportIataCode(cityName, countryIsoCode);
    }
}
