package com.codecool.travelhelper.API.rapidapi.service.apiservice;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.BookingApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.bookingclient.BookingClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    @Autowired
    BookingClientImpl bookingClientImpl;

    public BookingApiModel getBooking(String cityName, String countryName){
        BookingApiModel response = bookingClientImpl.getBookingByCity(cityName, countryName);
        return response;
    }
}