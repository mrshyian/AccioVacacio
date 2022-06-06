package com.codecool.travelhelper.API.rapidapi.controller.apicontroller;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.WeatherServiceRapidApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class WeatherControllerRapidApi {
    @Autowired
    private final WeatherServiceRapidApi weatherService;

    @GetMapping("/")
    public String test() {
        return "test";
    }

    @GetMapping("/weather/{cityName}")
    public WeatherDtoRapidApi getWeather(@PathVariable String cityName){
        return weatherService.getWeather(cityName);
    }
}
