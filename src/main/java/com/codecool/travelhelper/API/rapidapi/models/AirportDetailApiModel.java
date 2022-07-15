package com.codecool.travelhelper.API.rapidapi.models;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AirportDetailApiModel {
    private String name;
    private String location;
    private String streetNumber;
    private String street;
    private String city;
    private String state;
    private String phone;
    private String website;

    @Override
    public String toString() {
        return "AirportDetailDtoRapidApi{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
