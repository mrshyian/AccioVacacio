package com.codecool.travelhelper.API.rapidapi.apimodel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeatherApiModel {
    private String description;
    private int temperature;
    private int feelsLike;
    private int pressure;
    private int humidity;
    private float wingSpeed;


    @Override
    public String toString() {
        return "WeatherDto{" +
                ", description='" + description + '\'' +
                ", temperature=" + temperature +
                ", feelsLike=" + feelsLike +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", wingSpeed=" + wingSpeed +
                '}';
    }
}
