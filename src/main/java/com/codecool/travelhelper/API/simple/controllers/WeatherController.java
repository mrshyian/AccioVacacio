package com.codecool.travelhelper.API.simple.controllers;


import com.codecool.travelhelper.API.simple.model.WeatherDto;
import com.codecool.travelhelper.API.simple.services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping("/weather")
    public WeatherDto getWeather(Model model) {
//        model.addAttribute("weather", weatherService.getWeather());
        return weatherService.getWeather();
    }
}
