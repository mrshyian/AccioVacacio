package com.codecool.travelhelper.API.rapidapi.controller.apicontroller;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.model.apimodel.touristattractions.PopularAttractionDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.PopularAttractionServiceRapidApi;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.WeatherServiceRapidApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PopularAttractionsControllerRapidApi {
    @Autowired
    private final PopularAttractionServiceRapidApi popularAttractionService;

    @GetMapping("/attractions/{cityName}/{countryIsoCode}")
    public List<PopularAttractionDtoRapidApi> getPopularAttractions(@PathVariable String cityName, @PathVariable String countryIsoCode){
        return popularAttractionService.getPopularAttractions(cityName, countryIsoCode, 2000, 5);
    }

}
