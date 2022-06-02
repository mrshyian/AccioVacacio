package com.codecool.travelhelper.API.rapidapi.model.apimodel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AirportDtoRapidApi {
    String AirportName;
    String AirportCode;

    @Override
    public String toString() {
        return "AirportDtoRapidApi{" +
                "AirportName='" + AirportName + '\'' +
                ", AirportCode='" + AirportCode + '\'' +
                '}';
    }
}


