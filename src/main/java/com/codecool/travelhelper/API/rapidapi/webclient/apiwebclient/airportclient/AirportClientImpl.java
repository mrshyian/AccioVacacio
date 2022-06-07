package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.airportclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClient;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

@Component
public class AirportClientImpl extends ApiWebClient {

    public AirportClientImpl()  {
        super(ApiMetaData.AIRPORT);
    }

    public AirportApiModel getCityAirport(String cityName) {
        this.setUrl("https://world-airports-directory.p.rapidapi.com/v1/airports/");
        String currentUrl = this.getUrl();
        String newUrl = currentUrl + cityName;
        this.setUrl(newUrl);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData());

        return getAirportDto(response);
    }

    public AirportApiModel getAirportDto(JsonObject response) {
        String airportName = getValueByKeyFromJsonObjectInsideJsonArray("AirportName", "results", response, 0);
        String airportCode = getValueByKeyFromJsonObjectInsideJsonArray("AirportCode", "results" , response, 0);

        return AirportApiModel.builder()
                .AirportName(airportName)
                .AirportCode(airportCode)
                .build();
    }
}



