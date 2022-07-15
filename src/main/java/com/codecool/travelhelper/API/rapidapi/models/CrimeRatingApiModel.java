package com.codecool.travelhelper.API.rapidapi.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CrimeRatingApiModel {
    private String index;
    private String city;

    @Override
    public String toString() {
        return "CrimeRatingRapidApi{" +
                "place=" + index +
                ", city='" + city + '\'' +
                '}';
    }
}