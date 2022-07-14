package com.codecool.travelhelper.API.simple.webclient;

import com.codecool.travelhelper.API.simple.models.ExchangeDto;
import com.codecool.travelhelper.API.simple.webclient.dto.OpenEmergencyNumbersEmergencyNumbersDto;
import com.codecool.travelhelper.API.simple.webclient.dto.OpenExchangeDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;

@Component
public class ExchangeClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String exchangeApiKey = "7b0ca5024967aea82cd94daf/";
    private final static String EXCHANGE_URL = "https://v6.exchangerate-api.com/v6/" + exchangeApiKey + "/pair/";

    public ExchangeDto getExchangeData(String countryFrom, String countryTo, String howMuchToConvert){

        String secondPartUrl = countryFrom + "/" + countryTo + "/" + howMuchToConvert;
        OpenExchangeDto exchangeDto =  callGetMethode(secondPartUrl,
                OpenExchangeDto.class);

        double convertRating = exchangeDto.getConversion_rate();

        String howMuchAfterConvert = String.valueOf(new DecimalFormat("##.##").format(convertRating * Double.parseDouble(howMuchToConvert)));


        return ExchangeDto.builder()
                .howMuchToConvert(howMuchToConvert)
                .howMuchAfterConvert(howMuchAfterConvert)
                .countryFrom(countryFrom)
                .countryTo(countryTo)
                .build();
    }

    public <T> T callGetMethode(String url, Class<T> responseType) {
        return restTemplate.getForObject(EXCHANGE_URL + url, responseType);
    }
}
