package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.weatherclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherDtoRapidApi;


public interface WeatherClientRapidApi {

    WeatherDtoRapidApi getCityWeather(String cityName);

}
