package com.codecool.travelhelper.API.rapidapi.model.apimodel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeatherDtoRapidApi {
    private String mainParameter;
    private String description;
    private String icon;
    private float temperature;
    private float feelsLike;
    private int pressure;
    private int humidity;
    private float wingSpeed;


    @Override
    public String toString() {
        return "WeatherDto{" +
                "mainParameter='" + mainParameter + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                ", temperature=" + temperature +
                ", feelsLike=" + feelsLike +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", wingSpeed=" + wingSpeed +
                '}';
    }
}
