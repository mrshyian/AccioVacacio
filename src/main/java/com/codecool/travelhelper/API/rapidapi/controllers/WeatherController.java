package com.codecool.travelhelper.API.rapidapi.controllers;

import com.codecool.travelhelper.API.rapidapi.models.WeatherApiModel;
import com.codecool.travelhelper.API.rapidapi.services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class WeatherController {

    @Autowired
    private final WeatherService weatherService;

    @GetMapping("/")
    public String test() {
        return "test";
    }

    @GetMapping("/weather/{cityName}/{countryName}")
    public WeatherApiModel getWeather(@PathVariable String cityName, @PathVariable String countryName){
        return weatherService.getWeather(cityName, countryName);
    }
}
