package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.airportclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaDataRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClientRapidApi;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

@Component
public class AirportClientImplRapidApi extends ApiWebClientRapidApi {

    public AirportClientImplRapidApi()  {
        super(ApiMetaDataRapidApi.AIRPORT);
    }

    public AirportDtoRapidApi getCityAirport(String cityName) {
        this.setUrl("https://world-airports-directory.p.rapidapi.com/v1/airports/");
        String currentUrl = this.getUrl();
        String newUrl = currentUrl + cityName;
        this.setUrl(newUrl);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData());

        return getAirportDto(response);
    }

    public AirportDtoRapidApi getAirportDto(JsonObject response) {
        String airportName = getValueByKeyFromJsonObjectInsideJsonArray("AirportName", "results", response, 0);
        String airportCode = getValueByKeyFromJsonObjectInsideJsonArray("AirportCode", "results" , response, 0);

        return AirportDtoRapidApi.builder()
                .AirportName(airportName)
                .AirportCode(airportCode)
                .build();
    }
}



