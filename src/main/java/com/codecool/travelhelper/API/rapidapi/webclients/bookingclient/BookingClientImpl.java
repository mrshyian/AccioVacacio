package com.codecool.travelhelper.API.rapidapi.webclients.bookingclient;

import com.codecool.travelhelper.API.rapidapi.models.BookingApiModel;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiWebClient;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiMetaData;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookingClientImpl extends ApiWebClient {

    public BookingClientImpl() {
        super(ApiMetaData.BOOKING);
    }

    public BookingApiModel getBookingByCity(String cityName, String countryName) {
        Map<String, Object> parameters = new HashMap<>() {{
            put("cityName", cityName);
            put("countryName", countryName);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

        return getBookingDto(response);
    }

    public BookingApiModel getBookingDto(JsonObject response) {
        String name = getValueByKeyFromJsonObject("name", response);
        String link = getValueByKeyFromJsonObject("link", response);
        String rating = getValueByKeyFromJsonObject("rating", response);


        return BookingApiModel.builder()
                .name(name)
                .link(link)
                .rating(rating)
                .build();
    }

}
