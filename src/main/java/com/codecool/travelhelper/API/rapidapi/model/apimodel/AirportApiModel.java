package com.codecool.travelhelper.API.rapidapi.model.apimodel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AirportApiModel {
    String AirportName;
    String AirportCode;
    String cityName;
    String countryName;

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


