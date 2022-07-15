package com.codecool.travelhelper.API.rapidapi.services;

import com.codecool.travelhelper.API.rapidapi.models.CrimeRatingApiModel;
import com.codecool.travelhelper.API.rapidapi.webclients.crimeratingclient.CrimeRatingClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CrimeRatingService {

    @Autowired
    private CrimeRatingClientImpl crimeRatingClient;

    public List<CrimeRatingApiModel> getCrimeRating(String cityName, String countryName){
        return crimeRatingClient.getCrimeRating(cityName, countryName);
    }

}
