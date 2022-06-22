package com.codecool.travelhelper.API.rapidapi.apiwebclient.touristattractions;

import com.codecool.travelhelper.API.rapidapi.apimodel.touristattractions.PopularAttractionDto;

import java.util.List;

public interface PopularAttractionsClient {
    List<PopularAttractionDto> getPopularAttractions(String cityName, String countryIsoCode, int distance, int amountOfAttractions);
}
