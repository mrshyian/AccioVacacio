package com.codecool.travelhelper.API.rapidapi.model.apimodel;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AirportDetailDtoRapidApi {
    String name;
    String location;
    String streetNumber;
    String street;
    String city;
    String state;
    String phone;
    String website;

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
