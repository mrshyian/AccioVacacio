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
        String findBy = cityName + " " + countryName;
        Map<String, Object> parameters = new HashMap<>(){{
            put("q", findBy);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

        return getWeatherDto(response, cityName, countryName);
    }

    private WeatherApiModel getWeatherDto (JsonObject response, String cityName, String countryName){
        String description = getValueByKeyFromThreeTimesNestedJsonObject("text", "current", "condition",  response);

        int temperature = (int)(Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonObject("temp_c", "current", response)));
        int feelsLike = (int)(Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonObject("feelslike_c","current", response)));
        int pressure = (int)(Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonObject("pressure_mb", "current", response)));
        int humidity = Integer.parseInt(getValueByKeyFromJsonObjectInsideJsonObject("humidity", "current", response));

        float wingSpeed = Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonObject("wind_mph", "current", response));

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

