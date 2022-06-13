package com.codecool.travelhelper.API.rapidapi.controller.apicontroller;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.CrimeRatingApiModel;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.CrimeRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CrimeRatingApiController {


    @Autowired
    CrimeRatingService crimeRatingService;

    @GetMapping("/crime_rating")
    public List<CrimeRatingApiModel> getAirport(){
        return crimeRatingService.getCrimeRating();
    }

}
