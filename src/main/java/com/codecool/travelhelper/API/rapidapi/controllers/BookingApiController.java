package com.codecool.travelhelper.API.rapidapi.controllers;

import com.codecool.travelhelper.API.rapidapi.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class BookingApiController {

    @Autowired
    BookingService bookingService;

//    @GetMapping("/booking/{cityName}/{countryName}")
//    public BookingApiModel getBooking(@PathVariable String cityName, @PathVariable String countryName){
//        return bookingService.getBooking(cityName, countryName);
//    }
}
