package com.codecool.travelhelper.API.simple.webclient.weather.dto;


import lombok.Getter;

@Getter
public class OpenWeatherMainDto {
    private float temp;
    private float feels_like;
    private int pressure;
    private int humidity;
}
