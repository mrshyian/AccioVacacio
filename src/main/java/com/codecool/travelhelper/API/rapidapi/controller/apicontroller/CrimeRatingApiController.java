package com.codecool.travelhelper.API.rapidapi.controller.apicontroller;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.CrimeRatingApiModel;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.CrimeRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CrimeRatingApiController {

    @Autowired
    CrimeRatingService crimeRatingService;

    @GetMapping("/crime_rating/{cityName}/{countryName}")
    public List<CrimeRatingApiModel> getAirport(@PathVariable String cityName, @PathVariable String countryName){
        return crimeRatingService.getCrimeRating(cityName, countryName);
    }

}
