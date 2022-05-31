package com.codecool.travelhelper.API.simple.services;

import com.codecool.travelhelper.API.simple.model.WeatherDto;

import com.codecool.travelhelper.API.simple.webclient.weather.WeatherClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherDto getWeather(){
        return weatherClient.getWeatherForCity("Warszawa");
    }
}
