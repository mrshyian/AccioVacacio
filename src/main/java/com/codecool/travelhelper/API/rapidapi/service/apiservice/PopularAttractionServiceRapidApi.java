package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.model.apimodel.touristattractions.PopularAttractionDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.touristattractions.PopularAttractionsClientImplRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.weatherclient.WeatherClientImplRapidApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopularAttractionServiceRapidApi {
    @Autowired
    private final PopularAttractionsClientImplRapidApi popularAttractionsClient;

    public List<PopularAttractionDtoRapidApi> getPopularAttractions(String cityName, String countryIsoCode, int distance, int amountOfAttractions){
        List<PopularAttractionDtoRapidApi> response = popularAttractionsClient.getPopularAttractions(cityName, countryIsoCode, distance, amountOfAttractions);
        return response;
    }
}
