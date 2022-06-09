package com.codecool.travelhelper.API.rapidapi.model.apimodel.touristattractions;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PopularAttractionDtoRapidApi {
    String locationName;
    String locationAddress;
    int distanceToLocation;
    String website;
}
