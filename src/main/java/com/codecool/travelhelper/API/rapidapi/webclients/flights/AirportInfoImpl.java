package com.codecool.travelhelper.API.rapidapi.webclients.flights;


import com.codecool.travelhelper.API.rapidapi.models.flights.AirportInfoApiModel;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiWebClient;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AirportInfoImpl extends ApiWebClient {
    public AirportInfoImpl() { super(ApiMetaData.AIRPORT_INFO); }

    public AirportInfoApiModel getAirportData(String airportIataCode) {
        Map<String, Object> parameters = new HashMap<>() {{
            put("iata", airportIataCode);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

        return getAirportInfoDto(response);
    }

    private AirportInfoApiModel getAirportInfoDto(JsonObject response) {
        String iataCode = getValueByKeyFromJsonObject("iata", response);
        String icaoCode = getValueByKeyFromJsonObject("icao", response);
        String name = getValueByKeyFromJsonObject("name", response);

        String city = getValueByKeyFromJsonObject("city", response);
        String street = getValueByKeyFromJsonObject("street", response);
        String streetNumber = getValueByKeyFromJsonObject("street_number", response);
        String address = city.toUpperCase() + "\n" + street.toUpperCase() + ", " + streetNumber;

        String phone = getValueByKeyFromJsonObject("phone", response);
        String website = getValueByKeyFromJsonObject("website", response);

        return AirportInfoApiModel.builder()
                .iataCode(iataCode)
                .icaoCode(icaoCode)
                .name(name)
                .address(address)
                .phone(phone)
                .website(website)
                .build();
    }


}