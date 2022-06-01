package com.codecool.travelhelper.API.simple.webclient.weather.dto;

import lombok.Getter;

@Getter
public class OpenWeatherWeatherDto {
    private OpenWeatherMainDto main;
    private OpenWeatherWindDto wind;
    String name;
}
