package com.codecool.travelhelper.API.rapidapi.services;

import com.codecool.travelhelper.API.rapidapi.models.WorldNewsApiModel;
import com.codecool.travelhelper.API.rapidapi.webclients.newsclient.WorldNewsClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorldNewsService {
    @Autowired
    private final WorldNewsClientImpl newsClient;

    public List<WorldNewsApiModel> getNews(String cityName, String countryName){
        int amountOfNews = 3;
        List<WorldNewsApiModel> response = newsClient.getCityNews(cityName, amountOfNews, countryName);
        return response;
    }
}
