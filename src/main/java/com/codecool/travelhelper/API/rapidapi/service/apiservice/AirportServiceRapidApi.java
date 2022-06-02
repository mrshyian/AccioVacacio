package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.airportclient.AirportClientImplRapidApi;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportServiceRapidApi {

    @Autowired
    AirportClientImplRapidApi airportClientImplRapidApi;

    public AirportDtoRapidApi getAirport(String cityName){
        AirportDtoRapidApi response = airportClientImplRapidApi.getCityAirport(cityName);
        return response;
    }

}
