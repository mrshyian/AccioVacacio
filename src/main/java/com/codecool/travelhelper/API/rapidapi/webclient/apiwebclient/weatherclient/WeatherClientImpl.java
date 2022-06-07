package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.weatherclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClient;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WeatherClientImpl extends ApiWebClient implements WeatherClient {

    public WeatherClientImpl() {
        super(ApiMetaData.WEATHER);
    }

    public WeatherApiModel getCityWeather(String cityName) {
        Map<String, Object> parameters = new HashMap<>(){{
            put("q", cityName);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

        return getWeatherDto(response);
    }

    private WeatherApiModel getWeatherDto (JsonObject response){
        String mainParameter = getValueByKeyFromJsonObjectInsideJsonArray("main", "weather", response);
        String description = getValueByKeyFromJsonObjectInsideJsonArray("description", "weather", response);
        String icon = getValueByKeyFromJsonObjectInsideJsonArray("src/main/frontend/src/icon", "weather", response);

        int temperature = (int)(Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonObject("temp", "main", response)) - 273.15);
        int feelsLike = (int)(Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonObject("feels_like", "main", response)) - 273.15);
        int pressure = Integer.parseInt(getValueByKeyFromJsonObjectInsideJsonObject("pressure", "main", response));
        int humidity = Integer.parseInt(getValueByKeyFromJsonObjectInsideJsonObject("humidity", "main", response));

        float wingSpeed = Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonObject("speed", "wind", response));

        return WeatherApiModel.builder()
                .mainParameter(mainParameter)
                .description(description.substring(0, 1).toUpperCase() + description.substring(1))
                .icon(icon)
                .temperature(temperature)
                .feelsLike(feelsLike)
                .pressure(pressure)
                .humidity(humidity)
                .wingSpeed(wingSpeed)
                .build();
    }
}

