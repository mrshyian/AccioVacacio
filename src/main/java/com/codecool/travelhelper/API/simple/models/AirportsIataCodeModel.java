package com.codecool.travelhelper.API.simple.models;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AirportsIataCodeModel {
    private String airportIataCode;
    private float latitude;
    private float longitude;
}


