package com.codecool.travelhelper.API.rapidapi.controller.apicontroller;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WorldNewsDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.WorldNewsServiceRapidApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorldNewsControllerRapidApi {

    @Autowired
    private final WorldNewsServiceRapidApi worldNewsServiceRapidApi;


    @GetMapping("/news/{cityName}")
    public WorldNewsDtoRapidApi getNews(@PathVariable String cityName){
        return worldNewsServiceRapidApi.getNews(cityName);
    }
}
