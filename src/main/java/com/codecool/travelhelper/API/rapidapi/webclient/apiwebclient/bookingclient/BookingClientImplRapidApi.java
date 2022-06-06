package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.bookingclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.BookingDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaDataRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClientRapidApi;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookingClientImplRapidApi extends ApiWebClientRapidApi {

    public BookingClientImplRapidApi() {
        super(ApiMetaDataRapidApi.BOOKING);
    }

    public BookingDtoRapidApi getBookingByCity(String cityName, String countryName) {
        Map<String, Object> parameters = new HashMap<>() {{
            put("cityName", cityName);
            put("countryName", countryName);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());
        System.out.println(response);

        BookingDtoRapidApi bookingDtoRapidApi = getBookingDto(response);
        System.out.println(bookingDtoRapidApi.toString());

        return bookingDtoRapidApi;
    }

    public BookingDtoRapidApi getBookingDto(JsonObject response) {
        String name = getValueByKeyFromJsonObject("name", response);
        String link = getValueByKeyFromJsonObject("link", response);
        String rating = getValueByKeyFromJsonObject("rating", response);


        BookingDtoRapidApi bookingDto = BookingDtoRapidApi.builder()
                .name(name)
                .link(link)
                .rating(rating)
                .build();
        System.out.println(bookingDto);
        return bookingDto;
    }

}
