package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.weatherclient.WeatherClientImplRapidApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherServiceRapidApi {
    @Autowired
    private final WeatherClientImplRapidApi weatherClient;

    public WeatherDtoRapidApi getWeather(String cityName){
        WeatherDtoRapidApi response = weatherClient.getCityWeather(cityName);
        return response;
    }
}
