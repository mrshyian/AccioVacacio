package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.newsclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WorldNewsApiModel;

import java.util.List;

public interface WorldNewsClient {
    List<WorldNewsApiModel> getCityNews(String cityName, int amountOfNews);
}
