package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.model.apimodel.WorldNewsDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.newsclient.WorldNewsClientImplRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.weatherclient.WeatherClientImplRapidApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorldNewsServiceRapidApi {
    @Autowired
    private final WorldNewsClientImplRapidApi newsClient;

    public WorldNewsDtoRapidApi getNews(String cityName){
        WorldNewsDtoRapidApi response = newsClient.getCityNews(cityName);
        return response;
    }
}
