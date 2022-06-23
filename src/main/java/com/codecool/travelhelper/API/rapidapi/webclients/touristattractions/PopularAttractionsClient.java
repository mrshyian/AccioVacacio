package com.codecool.travelhelper.API.rapidapi.webclients.touristattractions;

import com.codecool.travelhelper.API.rapidapi.models.touristattractions.PopularAttractionDto;

import java.util.List;

public interface PopularAttractionsClient {
    List<PopularAttractionDto> getPopularAttractions(String cityName, String countryIsoCode, int distance, int amountOfAttractions);
}
