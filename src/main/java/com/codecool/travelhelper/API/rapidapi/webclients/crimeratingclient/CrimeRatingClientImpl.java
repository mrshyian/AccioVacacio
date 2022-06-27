package com.codecool.travelhelper.API.rapidapi.webclients.crimeratingclient;

import com.codecool.travelhelper.API.rapidapi.models.CrimeRatingApiModel;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiWebClient;
import com.codecool.travelhelper.aws.database.repositories.CrimeRatingRepository;
import com.codecool.travelhelper.aws.database.models.CrimeRatingTable;
import com.codecool.travelhelper.aws.database.repositories.jdbc.CrimeRatingRepositoryImpl;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrimeRatingClientImpl extends ApiWebClient {

    @Autowired
    CrimeRatingRepositoryImpl crimeRatingRepositoryImpl;

    public CrimeRatingClientImpl()  {
        super(ApiMetaData.CRIME_RATING);
    }

    public List<CrimeRatingApiModel> getCrimeRating(String cityName, String countryName) {
        this.setUrl("https://ranked-crime-cities.p.rapidapi.com/Kc4Qth/ranked_crime_cities");

        JsonArray response = getApiResponseAsList(this.getUrl(), this.getHeadersData());

        return getCrimeRatingDto(response, cityName, countryName);
    }

    public List<CrimeRatingApiModel> getCrimeRatingDto(JsonArray response, String cityName, String countryName) {
        List<CrimeRatingApiModel> crimeRatingApiModels = new ArrayList<>();
        int indexOfSearchingCity = 111;
        for (int i=1; i<100; i++) {
            String index = getValueByKeyFromJsonObjectInsideList("id", response, i);
            String city = getValueByKeyFromJsonObjectInsideList("Name", response, i);
            city = city.split(",")[0];

            if (city.equals(cityName)){
                indexOfSearchingCity=i;
            }

            CrimeRatingApiModel singleCityCrimeRating = CrimeRatingApiModel.builder()
                    .index(index)
                    .city(city)
                    .build();

            crimeRatingApiModels.add(singleCityCrimeRating);
        }

        int starsCount = 0;
        if (indexOfSearchingCity< 20){
            starsCount = 5;
        } else if (indexOfSearchingCity < 40){
            starsCount = 4;
        } else if (indexOfSearchingCity < 60){
            starsCount = 3;
        } else if (indexOfSearchingCity < 80){
            starsCount = 2;
        } else if (indexOfSearchingCity < 100){
            starsCount = 1;
        }

        //----------------------------saving emergency numbers to database----------------------------
        crimeRatingRepositoryImpl.setCrimeRatingDataByCityAndCountryName(
                new CrimeRatingTable(
                        cityName,
                        countryName,
                        starsCount
                )
        );
        //--------------------------------------------------------------------------------------------

        return crimeRatingApiModels;
    }
}



