package com.codecool.travelhelper.API.rapidapi.controllers;

import com.codecool.travelhelper.API.rapidapi.models.WeatherApiModel;
import com.codecool.travelhelper.API.rapidapi.services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/registration")
    public String registration(@RequestBody String data){
        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject)jsonParser.parse(data);
        String fullName = commentJsonObject.get("fullName").getAsString();
        String nickName = commentJsonObject.get("nickName").getAsString();
        String birthday = commentJsonObject.get("birthday").getAsString();
        String email = commentJsonObject.get("email").getAsString();
        String password = commentJsonObject.get("password").getAsString();
        String repeatPassword = commentJsonObject.get("repeatPassword").getAsString();
        return data;
    }
}
