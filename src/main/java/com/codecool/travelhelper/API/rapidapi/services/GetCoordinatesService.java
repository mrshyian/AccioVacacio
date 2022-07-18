package com.codecool.travelhelper.API.rapidapi.services;

import com.codecool.travelhelper.API.rapidapi.models.GetCoordinatesModel;
import com.codecool.travelhelper.API.rapidapi.webclients.getCoordinatesClients.GetCoordinatesClientImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCoordinatesService {

    @Autowired
    private GetCoordinatesClientImpl getCoordinatesClient;

    public GetCoordinatesModel getCoordinates(String cityName){
        return getCoordinatesClient.getCityCoordinates(cityName);
    }
}
