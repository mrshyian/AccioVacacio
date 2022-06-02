package com.codecool.travelhelper.API.rapidapi.controller.apicontroller;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportDetailDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.AirportDetailServiceRapidApi;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.AirportServiceRapidApi;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AirportDetailApiController {

    @Autowired
    AirportDetailServiceRapidApi airportDetailServiceRapidApi;

    @GetMapping("/airport-details/{airportCode}")
    public AirportDetailDtoRapidApi getAirportDetail(@PathVariable String airportCode){
        return airportDetailServiceRapidApi.getAirportDetail(airportCode);
    }
}
