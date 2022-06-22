package com.codecool.travelhelper.API.rapidapi.services;

import com.codecool.travelhelper.API.rapidapi.models.AirportDetailApiModel;
import com.codecool.travelhelper.API.rapidapi.webclients.airportclient.AirportDetailClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportDetailService {

    @Autowired
    AirportDetailClientImpl airportDetailClientImpl;

    public AirportDetailApiModel getAirportDetail(String cityCode, String cityName, String countryName){
        AirportDetailApiModel response = airportDetailClientImpl.getCityAirportByIata(cityCode, cityName, countryName);
        return response;
    }

}
