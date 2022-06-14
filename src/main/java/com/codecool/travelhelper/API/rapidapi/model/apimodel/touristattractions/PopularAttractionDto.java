package com.codecool.travelhelper.API.rapidapi.model.apimodel.touristattractions;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.BingImageSearch;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PopularAttractionDto {
    String locationName;
    String locationAddress;
    int distanceToLocation;
    String website;
    BingImageSearch imageUrl;
}
