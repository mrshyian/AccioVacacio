package com.codecool.travelhelper.API.rapidapi.models.touristattractions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TrueWayPlacesDto {
    private String locationName;
    private String locationAddress;
    private int distanceToLocation;
    private String website;


    @Override
    public String toString() {
        return "TrueWayPlacesDto{" +
                "locationName='" + locationName + '\'' +
                ", locationAddress='" + locationAddress + '\'' +
                ", distanceToLocation=" + distanceToLocation +
                ", website='" + website + '\'' +
                '}';
    }
}
