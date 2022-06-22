package com.codecool.travelhelper.API.rapidapi.apiwebclient.airportclient;


import com.codecool.travelhelper.API.rapidapi.apimodel.AirportDetailApiModel;
import com.codecool.travelhelper.API.rapidapi.apiwebclient.ApiWebClient;
import com.codecool.travelhelper.API.rapidapi.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.aws.database.repositories.search_city_repositories.AirportDetailsRepository;
import com.codecool.travelhelper.aws.database.tables.search_city_tables.AirportDetailsTable;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AirportDetailClientImpl extends ApiWebClient {
    @Autowired
    AirportDetailsRepository airportDetailsRepository;

    public AirportDetailClientImpl() {
        super(ApiMetaData.AIRPORT_DETAIL);
    }

    public AirportDetailApiModel getCityAirportByIata(String cityCode, String cityName, String countryName){
        this.setUrl("https://airport-info.p.rapidapi.com/airport?iata=");
        String currentUrl = this.getUrl();
        String newUrl = currentUrl + cityCode;
        this.setUrl(newUrl);
        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData());

        return getAirportDetailDto(response, cityName, countryName);
    }

    public AirportDetailApiModel getAirportDetailDto(JsonObject response, String cityName, String countryName){
        String name = getValueByKeyFromJsonObject("name", response);
        String location = getValueByKeyFromJsonObject("location", response);
        String streetNumber = getValueByKeyFromJsonObject("street_number", response);
        String street = getValueByKeyFromJsonObject("street", response);
        String city = getValueByKeyFromJsonObject("city", response);
        String state = getValueByKeyFromJsonObject("state", response);
        String phone = getValueByKeyFromJsonObject("phone", response);
        String website = getValueByKeyFromJsonObject("website", response);

        // Long searchingPlaceId = 1L;
        //----------------------------saving emergency numbers to database----------------------------
        airportDetailsRepository.save(
                new AirportDetailsTable(
                        cityName,
                        countryName,
                        name,
                        location,
                        streetNumber,
                        street,
                        city,
                        state,
                        phone,
                        website
                )
        );
        //--------------------------------------------------------------------------------------------

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
