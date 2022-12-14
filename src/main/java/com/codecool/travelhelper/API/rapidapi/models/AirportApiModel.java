package com.codecool.travelhelper.API.rapidapi.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AirportApiModel {
    private String AirportName;
    private String AirportCode;
    private String cityName;
    private String countryName;

    @Override
    public String toString() {
        return "AirportApiModel{" +
                "AirportName='" + AirportName + '\'' +
                ", AirportCode='" + AirportCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}


