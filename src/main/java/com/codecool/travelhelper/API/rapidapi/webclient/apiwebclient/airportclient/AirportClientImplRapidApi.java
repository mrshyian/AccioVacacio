package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.airportclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaDataRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClientRapidApi;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AirportClientImplRapidApi extends ApiWebClientRapidApi {

    public AirportClientImplRapidApi()  {
        super(ApiMetaDataRapidApi.AIRPORT);
    }

    public AirportDtoRapidApi getCityAirport(String cityName) {
        String currentUrl = this.getUrl();
        String newUrl = currentUrl + cityName;
        this.setUrl(newUrl);
        System.out.println(newUrl);
        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData());
        System.out.println(response);

        AirportDtoRapidApi airportDto = getAirportDto(response);
        System.out.println(airportDto.toString());

        return airportDto;
    }

    public AirportDtoRapidApi getAirportDto(JsonObject response) {
        String airportName = getValueByKeyFromJsonObjectInsideJsonArray("AirportName", "results", response, 0);
        String airportCode = getValueByKeyFromJsonObjectInsideJsonArray("AirportCode", "results" , response, 0);
        System.out.println(airportName);

        AirportDtoRapidApi airportDto = AirportDtoRapidApi.builder()
                .AirportName(airportName)
                .AirportCode(airportCode)
                .build();

        return airportDto;
    }
}



