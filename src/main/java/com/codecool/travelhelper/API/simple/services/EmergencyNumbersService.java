package com.codecool.travelhelper.API.simple.services;

import com.codecool.travelhelper.API.simple.model.EmergencyNumbersDto;

import com.codecool.travelhelper.API.simple.webclient.emergency_numbers.EmergencyNumbersClient;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmergencyNumbersService {
    @Autowired
    private final EmergencyNumbersClient emergencyNumbersClient;

    public EmergencyNumbersDto getEmergencyNumbers(String countryName, String cityName){
        CountryCode countryCode = CountryCode.findByName(countryName).get(0);

        return emergencyNumbersClient.getEmergencyNumbers(countryCode.toString(), countryName, cityName);
    }
}
