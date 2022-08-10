package com.codecool.travelhelper.API.rapidapi.services.flights;


import com.codecool.travelhelper.API.rapidapi.models.flights.AirportInfoApiModel;
import com.codecool.travelhelper.API.rapidapi.webclients.flights.AirportInfoImpl;
import com.codecool.travelhelper.API.simple.models.AirportsIataCodeModel;
import com.codecool.travelhelper.API.simple.services.AirportsIataCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportInfoService {

    @Autowired
    private AirportInfoImpl airportInfo;

    @Autowired
    private AirportsIataCodeService airportsIataCodeService;

    public AirportInfoApiModel getAirportData(String cityName, String countryIsoCode){
        AirportsIataCodeModel airportsIataCodeModel = airportsIataCodeService.getAirportIataCode(cityName, countryIsoCode);
        if (airportsIataCodeModel != null){
            String airportIataCode = airportsIataCodeModel.getAirportIataCode();
            AirportInfoApiModel airportInfoApiModel = airportInfo.getAirportData(airportIataCode);

            return airportInfoApiModel;
        }

        return null;
    }
}
