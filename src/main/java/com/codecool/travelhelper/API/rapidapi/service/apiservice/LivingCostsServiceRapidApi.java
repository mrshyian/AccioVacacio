package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.LivingCostsDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.livingcostsclient.LivingCostsClientImplRapidApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivingCostsServiceRapidApi {

    @Autowired
    private final LivingCostsClientImplRapidApi livingCostsClientImplRapidApi;

    public LivingCostsDtoRapidApi getLivingCosts(String cityName){
        LivingCostsDtoRapidApi response = livingCostsClientImplRapidApi.getCityLivingCosts(cityName);
        return response;
    }

}
