package com.codecool.travelhelper.API.rapidapi.models.touristattractions;

import com.codecool.travelhelper.API.rapidapi.models.BingImageSearch;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PopularAttractionDto {
    private String locationName;
    private String locationAddress;
    private int distanceToLocation;
    private String website;
    private BingImageSearch imageUrl;
}
