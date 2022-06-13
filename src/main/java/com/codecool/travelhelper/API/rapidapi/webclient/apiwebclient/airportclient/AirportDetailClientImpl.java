package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.airportclient;


import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportDetailApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClient;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

@Component
public class AirportDetailClientImpl extends ApiWebClient {
    public AirportDetailClientImpl() {
        super(ApiMetaData.AIRPORT_DETAIL);
    }

    public AirportDetailApiModel getCityAirportByIata(String cityName){
        this.setUrl("https://airport-info.p.rapidapi.com/airport?iata=");
        String currentUrl = this.getUrl();
        String newUrl = currentUrl + cityName;
        this.setUrl(newUrl);
        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData());

        return getAirportDetailDto(response);
    }

    public AirportDetailApiModel getAirportDetailDto(JsonObject response){
        String name = getValueByKeyFromJsonObject("name", response);
        String location = getValueByKeyFromJsonObject("location", response);
        String streetNumber = getValueByKeyFromJsonObject("street_number", response);
        String street = getValueByKeyFromJsonObject("street", response);
        String city = getValueByKeyFromJsonObject("city", response);
        String state = getValueByKeyFromJsonObject("state", response);
        String phone = getValueByKeyFromJsonObject("phone", response);
        String website = getValueByKeyFromJsonObject("website", response);

        return AirportDetailApiModel.builder()
                .name(name)
                .location(location)
                .streetNumber(streetNumber)
                .street(street)
                .city(city)
                .state(state)
                .phone(phone)
                .website(website)
                .build();
    }
}
