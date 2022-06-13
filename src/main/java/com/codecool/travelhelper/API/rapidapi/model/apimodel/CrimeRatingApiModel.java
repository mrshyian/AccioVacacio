package com.codecool.travelhelper.API.rapidapi.model.apimodel;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CrimeRatingApiModel {
    String index;
    String city;

    @Override
    public String toString() {
        return "CrimeRatingRapidApi{" +
                "place=" + index +
                ", city='" + city + '\'' +
                '}';
    }
}