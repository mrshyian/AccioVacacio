package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.weatherclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherApiModel;


public interface WeatherClient {

    WeatherApiModel getCityWeather(String cityName, String countryName);

}
