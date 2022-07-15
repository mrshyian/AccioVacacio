package com.codecool.travelhelper.API.rapidapi.services;

import com.codecool.travelhelper.API.rapidapi.models.BookingApiModel;
import com.codecool.travelhelper.API.rapidapi.webclients.bookingclient.BookingClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    @Autowired
    private BookingClientImpl bookingClientImpl;

    public BookingApiModel getBooking(String cityName, String countryName){
        BookingApiModel response = bookingClientImpl.getBookingByCity(cityName, countryName);
        return response;
    }
}
