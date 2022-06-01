package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.newsclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WorldNewsDtoRapidApi;

public interface WorldNewsClientRapidApi {
    WorldNewsDtoRapidApi getCityNews(String cityName);
}
