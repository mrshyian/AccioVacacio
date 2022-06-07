package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.LivingCostsApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.livingcostsclient.LivingCostsClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivingCostsService {

    @Autowired
    private final LivingCostsClientImpl livingCostsClientImpl;

    public List<LivingCostsApiModel> getLivingCosts(String cityName, String countryName){
        List<LivingCostsApiModel> response = livingCostsClientImpl.getCityLivingCosts(cityName, countryName);
        return response;
    }

}
