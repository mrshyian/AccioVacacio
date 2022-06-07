package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.weatherclient.WeatherClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {
    @Autowired
    private final WeatherClientImpl weatherClient;

    public WeatherApiModel getWeather(String cityName){
        WeatherApiModel response = weatherClient.getCityWeather(cityName);
        return response;
    }
}
