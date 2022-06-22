package com.codecool.travelhelper.API.rapidapi.apiwebclient.airportclient;

import com.codecool.travelhelper.API.rapidapi.apimodel.AirportApiModel;
import com.codecool.travelhelper.API.rapidapi.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.apiwebclient.ApiWebClient;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

@Component
public class AirportClientImpl extends ApiWebClient {

    public AirportClientImpl()  {
        super(ApiMetaData.AIRPORT);
    }

    public AirportApiModel getCityAirport(String cityName, String countryName) {
        this.setUrl("https://world-airports-directory.p.rapidapi.com/v1/airports/");
        String currentUrl = this.getUrl();
        String newUrl = currentUrl + cityName;
        this.setUrl(newUrl);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData());

        return getAirportDto(response, cityName, countryName);
    }

    public AirportApiModel getAirportDto(JsonObject response, String cityName, String countryName) {
        String airportName = getValueByKeyFromJsonObjectInsideJsonArray("AirportName", "results", response, 0);
        String airportCode = getValueByKeyFromJsonObjectInsideJsonArray("AirportCode", "results" , response, 0);

        return AirportApiModel.builder()
                .AirportName(airportName)
                .AirportCode(airportCode)
                .cityName(cityName)
                .countryName(countryName)
                .build();
    }
}



