package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.CrimeRatingApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.crimeratingclient.CrimeRatingClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CrimeRatingService {

    @Autowired
    CrimeRatingClientImpl crimeRatingClient;

    public List<CrimeRatingApiModel> getCrimeRating(String cityName, String countryName){
        return crimeRatingClient.getCrimeRating(cityName, countryName);
    }

}
