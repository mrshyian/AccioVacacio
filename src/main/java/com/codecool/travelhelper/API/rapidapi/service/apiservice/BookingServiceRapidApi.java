package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.model.apimodel.BookingDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.airportclient.AirportClientImplRapidApi;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.bookingclient.BookingClientImplRapidApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceRapidApi {

    @Autowired
    BookingClientImplRapidApi bookingClientImplRapidApi;

    public BookingDtoRapidApi getBooking(String cityName, String countryName){
        BookingDtoRapidApi response = bookingClientImplRapidApi.getBookingByCity(cityName, countryName);
        return response;
    }
}
