package com.codecool.travelhelper.API.rapidapi.webclients.weatherclient;

import com.codecool.travelhelper.API.rapidapi.models.WeatherApiModel;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiWebClient;
import com.codecool.travelhelper.aws.database.models.WeatherTable;
import com.codecool.travelhelper.aws.database.repositories.jdbc.WeatherRepositoryImpl;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherClientImpl extends ApiWebClient implements WeatherClient {

    @Autowired
    WeatherRepositoryImpl weatherRepositoryImpl;

    public WeatherClientImpl() {
        super(ApiMetaData.WEATHER);
    }

    public WeatherApiModel getCityWeather(String cityName, String countryName) {
        Map<String, Object> parameters = new HashMap<>(){{
            put("q", cityName);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

        return getWeatherDto(response, cityName, countryName);
    }

    private WeatherApiModel getWeatherDto (JsonObject response, String cityName, String countryName){
        String description = getValueByKeyFromJsonObjectInsideJsonArray("description", "weather", response);

        int temperature = (int)(Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonObject("temp", "main", response)) - 273.15);
        int feelsLike = (int)(Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonObject("feels_like", "main", response)) - 273.15);
        int pressure = Integer.parseInt(getValueByKeyFromJsonObjectInsideJsonObject("pressure", "main", response));
        int humidity = Integer.parseInt(getValueByKeyFromJsonObjectInsideJsonObject("humidity", "main", response));

        float wingSpeed = Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonObject("speed", "wind", response));

        //----------------------------saving weather to database----------------------------
        weatherRepositoryImpl.setWeatherDataByCityAndCountryName(new WeatherTable(cityName,
                countryName,
                description.substring(0, 1).toUpperCase() + description.substring(1),
                temperature,
                feelsLike,
                pressure,
                humidity,
                wingSpeed));
        //--------------------------------------------------------------------------------------------


        return WeatherApiModel.builder()
                .description(description.substring(0, 1).toUpperCase() + description.substring(1))
                .temperature(temperature)
                .feelsLike(feelsLike)
                .pressure(pressure)
                .humidity(humidity)
                .wingSpeed(wingSpeed)
                .build();
    }
}

