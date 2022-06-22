package com.codecool.travelhelper.API.rapidapi.apimodel.touristattractions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TrueWayPlacesDto {
    String locationName;
    String locationAddress;
    int distanceToLocation;
    String website;


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
