package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.weatherclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClient;
import com.codecool.travelhelper.aws.database.repositories.search_city_repositories.WeatherRepository;
import com.codecool.travelhelper.aws.database.tables.search_city_tables.AirportDetailsTable;
import com.codecool.travelhelper.aws.database.tables.search_city_tables.CrimeRatingTable;
import com.codecool.travelhelper.aws.database.tables.search_city_tables.WeatherTable;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class WeatherClientImpl extends ApiWebClient implements WeatherClient {

    @Autowired
    WeatherRepository weatherRepository;

    public WeatherClientImpl() {
        super(ApiMetaData.WEATHER);
    }

    public WeatherApiModel getCityWeather(String cityName, String countryName) {
        Map<String, Object> parameters = new HashMap<>(){{
            put("q", cityName);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

//        try {
//            return getWeatherDto(response, cityName, countryName);
//        } catch (Exception e) {
//            new ResponseEntity<List<WeatherTable>>(weatherRepository.findByCityNameAndCountryName(cityName, countryName), HttpStatus.OK).getBody()
//        }
        return getWeatherDto(response, cityName, countryName);
    }

    private WeatherApiModel getWeatherDto (JsonObject response, String cityName, String countryName){
        String description = getValueByKeyFromJsonObjectInsideJsonArray("description", "weather", response);

        int temperature = (int)(Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonObject("temp", "main", response)) - 273.15);
        int feelsLike = (int)(Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonObject("feels_like", "main", response)) - 273.15);
        int pressure = Integer.parseInt(getValueByKeyFromJsonObjectInsideJsonObject("pressure", "main", response));
        int humidity = Integer.parseInt(getValueByKeyFromJsonObjectInsideJsonObject("humidity", "main", response));

        float wingSpeed = Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonObject("speed", "wind", response));

        // Long searchingPlaceId = 1L;
        //----------------------------saving emergency numbers to database----------------------------
        weatherRepository.save(
                new WeatherTable(
                        cityName,
                        countryName,
                        description.substring(0, 1).toUpperCase() + description.substring(1),
                        temperature,
                        feelsLike,
                        pressure,
                        humidity,
                        wingSpeed
                )
        );
        //--------------------------------------------------------------------------------------------

        //----------------------------saving emergency numbers to database----------------------------
        String responseStr = (new ResponseEntity<List<WeatherTable>>(weatherRepository.findByCityNameAndCountryName(cityName, countryName), HttpStatus.OK).getBody().toString());
        int begin = responseStr.indexOf("(") + 1;
        int end = responseStr.lastIndexOf(")");
        responseStr = responseStr.substring(begin, end);
        System.out.println(responseStr);

        String[] responceList = responseStr.split(", ");

        String responceDescription = responceList[3].split("=")[1];
        int responceTemperature = Integer.parseInt(responceList[4].split("=")[1]);
        int responceFeelsLike = Integer.parseInt(responceList[5].split("=")[1]);
        int responcePressure = Integer.parseInt(responceList[6].split("=")[1]);
        int responceHumidity = Integer.parseInt(responceList[7].split("=")[1]);
        float responceWingSpeed = Float.parseFloat(responceList[8].split("=")[1]);

        System.out.println("responceDescription: " + responceDescription);
        System.out.println("responceTemperature: " + responceTemperature);
        System.out.println("responceFeelsLike: " + responceFeelsLike);
        System.out.println("responcePressure: " + responcePressure);
        System.out.println("responceHumidity: " + responceHumidity);
        System.out.println("responceWingSpeed: " + responceWingSpeed);
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

