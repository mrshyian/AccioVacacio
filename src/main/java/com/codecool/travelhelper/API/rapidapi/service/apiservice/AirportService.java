package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.airportclient.AirportClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportService {

    @Autowired
    AirportClientImpl airportClientImpl;

    public AirportApiModel getAirport(String cityName){
        AirportApiModel response = airportClientImpl.getCityAirport(cityName);
        return response;
    }

}
