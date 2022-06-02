package com.codecool.travelhelper.API.rapidapi.controller.apicontroller;


import com.codecool.travelhelper.API.rapidapi.model.apimodel.LivingCostsDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.LivingCostsServiceRapidApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LivingCostsApiController {

    @Autowired
    private final LivingCostsServiceRapidApi livingCostsServiceRapidApi;

    @GetMapping("/living-costs/{cityName}")
    public LivingCostsDtoRapidApi getLivingCosts(@PathVariable String cityName){
        return livingCostsServiceRapidApi.getLivingCosts(cityName);
    }

}
