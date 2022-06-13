package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.crimeratingclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportApiModel;
import com.codecool.travelhelper.API.rapidapi.model.apimodel.CrimeRatingApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CrimeRatingClientImpl extends ApiWebClient {

    public CrimeRatingClientImpl()  {
        super(ApiMetaData.CRIME_RATING);
    }

    public List<CrimeRatingApiModel> getCrimeRating() {
        this.setUrl("https://ranked-crime-cities.p.rapidapi.com/Kc4Qth/ranked_crime_cities");

        JsonArray response = getApiResponseAsList(this.getUrl(), this.getHeadersData());

        return getCrimeRatingDto(response);
    }

    public List<CrimeRatingApiModel> getCrimeRatingDto(JsonArray response) {
        List<CrimeRatingApiModel> crimeRatingApiModels = new ArrayList<>();

        for (int i=1; i<100; i++) {
            String index = getValueByKeyFromJsonObjectInsideList("id", response, i);
            String city = getValueByKeyFromJsonObjectInsideList("Name", response, i);

            city = city.split(",")[0];

            CrimeRatingApiModel singleCityCrimeRating = CrimeRatingApiModel.builder()
                    .index(index)
                    .city(city)
                    .build();

            crimeRatingApiModels.add(singleCityCrimeRating);
        }

        return crimeRatingApiModels;
    }
}



