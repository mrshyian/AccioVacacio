package com.codecool.travelhelper.API.simple.controllers;


import com.codecool.travelhelper.API.simple.model.EmergencyNumbersDto;
import com.codecool.travelhelper.API.simple.services.EmergencyNumbersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class EmergencyNumbersController {
    @Autowired
    private final EmergencyNumbersService emergencyNumbersService;

    @GetMapping("/emergency_numbers/{cityName}/{countryName}")
    public EmergencyNumbersDto getWeather(Model model, @PathVariable String countryName, @PathVariable String cityName) {

        return emergencyNumbersService.getEmergencyNumbers(countryName, cityName);
    }
}
