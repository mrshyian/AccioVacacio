package com.codecool.travelhelper.API.rapidapi.apiwebclient.newsclient;

import com.codecool.travelhelper.API.rapidapi.apimodel.WorldNewsApiModel;

import java.util.List;

public interface WorldNewsClient {
    List<WorldNewsApiModel> getCityNews(String cityName, int amountOfNews, String countryName);
}
