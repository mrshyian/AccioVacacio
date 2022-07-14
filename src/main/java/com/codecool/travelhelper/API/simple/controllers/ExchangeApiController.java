package com.codecool.travelhelper.API.simple.controllers;

import com.codecool.travelhelper.API.simple.models.ExchangeDto;
import com.codecool.travelhelper.API.simple.services.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExchangeApiController {

    @Autowired
    private ExchangeService exchangeService;


    @GetMapping("/exchange/{countryFrom}/{countryTo}/{howMuchToConvert}")
    public ExchangeDto getExchangeData(@PathVariable String countryFrom, @PathVariable String countryTo, @PathVariable String howMuchToConvert) {
        return exchangeService.getExchangeData(countryFrom, countryTo, howMuchToConvert);
    }
}
