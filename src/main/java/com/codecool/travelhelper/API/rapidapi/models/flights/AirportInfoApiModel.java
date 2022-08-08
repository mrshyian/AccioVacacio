package com.codecool.travelhelper.API.rapidapi.models.flights;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AirportInfoApiModel {
    String iataCode;
    String icaoCode;
    String name;
    String address;
    String phone;
    String website;
}
