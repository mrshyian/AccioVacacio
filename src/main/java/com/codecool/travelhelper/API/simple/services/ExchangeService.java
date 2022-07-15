package com.codecool.travelhelper.API.simple.services;

import com.codecool.travelhelper.API.simple.models.ExchangeDto;
import com.codecool.travelhelper.API.simple.webclient.ExchangeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeService {

    @Autowired
    private ExchangeClient exchangeClient;

    public ExchangeDto getExchangeData(String countryFrom, String countryTo, String howMuchToConvert){
        return exchangeClient.getExchangeData(countryFrom, countryTo, howMuchToConvert);
    }
}
