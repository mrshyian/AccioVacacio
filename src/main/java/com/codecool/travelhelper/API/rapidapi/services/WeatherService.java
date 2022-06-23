package com.codecool.travelhelper.API.rapidapi.services;

import com.codecool.travelhelper.API.rapidapi.models.WeatherApiModel;
import com.codecool.travelhelper.API.rapidapi.webclients.weatherclient.WeatherClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {
    @Autowired
    private final WeatherClientImpl weatherClient;

    public WeatherApiModel getWeather(String cityName, String countryName){
        WeatherApiModel response = weatherClient.getCityWeather(cityName, countryName);
        return response;
    }
}
