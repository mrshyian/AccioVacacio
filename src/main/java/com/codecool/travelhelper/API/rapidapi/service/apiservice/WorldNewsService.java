package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WorldNewsApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.newsclient.WorldNewsClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorldNewsService {
    @Autowired
    private final WorldNewsClientImpl newsClient;

    public List<WorldNewsApiModel> getNews(String cityName){
        int amountOfNews = 3;
        List<WorldNewsApiModel> response = newsClient.getCityNews(cityName, amountOfNews);
        return response;
    }
}
