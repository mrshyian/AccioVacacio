package com.codecool.travelhelper.API.rapidapi.services;

import com.codecool.travelhelper.API.rapidapi.models.touristattractions.PopularAttractionDto;
import com.codecool.travelhelper.API.rapidapi.webclients.touristattractions.PopularAttractionsClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PopularAttractionService {
    @Autowired
    private final PopularAttractionsClientImpl popularAttractionsClient;

    public List<PopularAttractionDto> getPopularAttractions(String cityName, String countryIsoCode, int distance, int amountOfAttractions){
        List<PopularAttractionDto> response = popularAttractionsClient.getPopularAttractions(cityName, countryIsoCode, distance, amountOfAttractions);
        return response;
    }
}
