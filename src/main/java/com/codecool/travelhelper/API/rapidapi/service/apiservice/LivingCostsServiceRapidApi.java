package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.LivingCostsDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.livingcostsclient.LivingCostsClientImplRapidApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivingCostsServiceRapidApi {

    @Autowired
    private final LivingCostsClientImplRapidApi livingCostsClientImplRapidApi;

    public List<LivingCostsDtoRapidApi> getLivingCosts(String cityName, String countryName){
        List<LivingCostsDtoRapidApi> response = livingCostsClientImplRapidApi.getCityLivingCosts(cityName, countryName);
        return response;
    }

}
