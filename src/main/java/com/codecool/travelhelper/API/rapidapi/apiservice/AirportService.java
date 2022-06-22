package com.codecool.travelhelper.API.rapidapi.apiservice;

import com.codecool.travelhelper.API.rapidapi.apimodel.AirportApiModel;
import com.codecool.travelhelper.API.rapidapi.apiwebclient.airportclient.AirportClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportService {

    @Autowired
    AirportClientImpl airportClientImpl;

    public AirportApiModel getAirport(String cityName, String countryName){
        AirportApiModel response = airportClientImpl.getCityAirport(cityName, countryName);
        return response;
    }

}
