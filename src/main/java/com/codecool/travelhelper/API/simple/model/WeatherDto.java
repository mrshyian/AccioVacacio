package com.codecool.travelhelper.API.simple.model;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WeatherDto {
    private float temperature;
    private float feelsLike;
    private int pressure;
    private int humidity;
    private float windSpeed;
    private String city;
}
