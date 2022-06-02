package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportDetailDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.airportclient.AirportDetailClientImplRapidApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportDetailServiceRapidApi {

    @Autowired
    AirportDetailClientImplRapidApi airportDetailClientImplRapidApi;

    public AirportDetailDtoRapidApi getAirportDetail(String cityName){
        AirportDetailDtoRapidApi response = airportDetailClientImplRapidApi.getCityAirportByIata(cityName);
        return response;
    }

}
