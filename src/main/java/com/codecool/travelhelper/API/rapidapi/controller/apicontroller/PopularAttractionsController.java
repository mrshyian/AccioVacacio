package com.codecool.travelhelper.API.rapidapi.controller.apicontroller;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.touristattractions.PopularAttractionDto;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.PopularAttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PopularAttractionsController {
    @Autowired
    private final PopularAttractionService popularAttractionService;

    @GetMapping("/attractions/{cityName}/{countryIsoCode}")
    public List<PopularAttractionDto> getPopularAttractions(@PathVariable String cityName, @PathVariable String countryIsoCode){
        int howMuchImages = 5;
        return popularAttractionService.getPopularAttractions(cityName, countryIsoCode, 2000, 5, howMuchImages);
    }

}
