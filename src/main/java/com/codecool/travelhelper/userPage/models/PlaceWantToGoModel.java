package com.codecool.travelhelper.userPage.models;


import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PlaceWantToGoModel {

    private List<String> imagesUrl;
    private String country;
    private String city;

    @Override
    public String toString() {
        return "PlaceWantToGoModel{" +
                "imagesUrl=" + imagesUrl +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
