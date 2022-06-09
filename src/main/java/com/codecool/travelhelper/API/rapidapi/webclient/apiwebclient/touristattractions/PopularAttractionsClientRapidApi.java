package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.touristattractions;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.touristattractions.PopularAttractionDtoRapidApi;

import java.util.List;

public interface PopularAttractionsClientRapidApi {
    List<PopularAttractionDtoRapidApi> getPopularAttractions(String cityName, String countryIsoCode, int distance, int amountOfAttractions);
}
