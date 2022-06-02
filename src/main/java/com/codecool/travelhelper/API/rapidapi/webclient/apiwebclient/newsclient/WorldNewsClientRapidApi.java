package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.newsclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WorldNewsDtoRapidApi;

import java.util.List;

public interface WorldNewsClientRapidApi {
    List<WorldNewsDtoRapidApi> getCityNews(String cityName, int amountOfNews);
}
