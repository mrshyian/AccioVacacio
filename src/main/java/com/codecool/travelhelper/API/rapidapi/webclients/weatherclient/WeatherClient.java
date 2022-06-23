package com.codecool.travelhelper.API.rapidapi.webclients.weatherclient;

import com.codecool.travelhelper.API.rapidapi.models.WeatherApiModel;


public interface WeatherClient {

    WeatherApiModel getCityWeather(String cityName, String countryName);

}
