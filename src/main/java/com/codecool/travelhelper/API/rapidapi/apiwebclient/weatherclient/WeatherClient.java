package com.codecool.travelhelper.API.rapidapi.apiwebclient.weatherclient;

import com.codecool.travelhelper.API.rapidapi.apimodel.WeatherApiModel;


public interface WeatherClient {

    WeatherApiModel getCityWeather(String cityName, String countryName);

}
