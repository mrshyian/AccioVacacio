package com.codecool.travelhelper.API.rapidapi.webclients.newsclient;

import com.codecool.travelhelper.API.rapidapi.models.WorldNewsApiModel;

import java.util.List;

public interface WorldNewsClient {
    List<WorldNewsApiModel> getCityNews(String cityName, int amountOfNews, String countryName);
}
