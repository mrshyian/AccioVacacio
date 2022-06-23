package com.codecool.travelhelper.API.rapidapi.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookingApiModel {
    String name;
    String link;
    String rating;

    @Override
    public String toString() {
        return "BookingDtoRapidApi{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
