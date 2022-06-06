package com.codecool.travelhelper.API.rapidapi.controller.apicontroller;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.AirportDetailDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.model.apimodel.BookingDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.service.apiservice.BookingServiceRapidApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class BookingApiController {

    @Autowired
    BookingServiceRapidApi bookingServiceRapidApi;

    @GetMapping("/booking/{cityName}/{countryName}")
    public BookingDtoRapidApi getBooking(@PathVariable String cityName, @PathVariable String countryName){
        return bookingServiceRapidApi.getBooking(cityName, countryName);
    }
}
