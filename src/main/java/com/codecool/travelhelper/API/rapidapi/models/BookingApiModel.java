package com.codecool.travelhelper.API.rapidapi.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookingApiModel {
    private String name;
    private String link;
    private String rating;

    @Override
    public String toString() {
        return "BookingDtoRapidApi{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}
