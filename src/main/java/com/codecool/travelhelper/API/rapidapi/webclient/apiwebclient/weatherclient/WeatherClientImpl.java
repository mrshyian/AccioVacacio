package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.weatherclient;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.WeatherApiModel;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClient;
import com.codecool.travelhelper.aws.database.repositories.search_city_repositories.WeatherRepository;
import com.codecool.travelhelper.aws.database.repositories.search_city_repositories.jdbc.WeatherRepositoryImpl;
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

//        try {
//            return getWeatherDto(response, cityName, countryName);
//        } catch (Exception e) {
//            //----------------------------getting weather from database----------------------------
//            String responseStr = (new ResponseEntity<List<WeatherTable>>(weatherRepository.findByCityNameAndCountryName(cityName, countryName), HttpStatus.OK).getBody().toString());
//
//            int begin = responseStr.indexOf("(") + 1;
//            int end = responseStr.lastIndexOf(")");
//            responseStr = responseStr.substring(begin, end);
//
//            String[] responseList = responseStr.split(", ");
//
//            String responseDescription = responseList[3].split("=")[1];
//            int responseTemperature = Integer.parseInt(responseList[4].split("=")[1]);
//            int responseFeelsLike = Integer.parseInt(responseList[5].split("=")[1]);
//            int responsePressure = Integer.parseInt(responseList[6].split("=")[1]);
//            int responseHumidity = Integer.parseInt(responseList[7].split("=")[1]);
//            float responseWingSpeed = Float.parseFloat(responseList[8].split("=")[1]);
//            return WeatherApiModel.builder()
//                    .description(responseDescription)
//                    .temperature(responseTemperature)
//                    .feelsLike(responseFeelsLike)
//                    .pressure(responsePressure)
//                    .humidity(responseHumidity)
//                    .wingSpeed(responseWingSpeed)
//                    .build();
//            //--------------------------------------------------------------------------------------------
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
        //----------------------------saving weather to database----------------------------
        WeatherTable newWeather = new WeatherTable(cityName,
                countryName,
                description.substring(0, 1).toUpperCase() + description.substring(1),
                temperature,
                feelsLike,
                pressure,
                humidity,
                wingSpeed);
        weatherRepositoryImpl.setWeatherDataByCityAndCountryName(newWeather);
        //--------------------------------------------------------------------------------------------

//        //----------------------------getting weather from database----------------------------
//        System.out.println("At start: " + new ResponseEntity<List<WeatherTable>>(weatherRepository.findByCityNameAndCountryName(cityName, countryName), HttpStatus.OK));
//        String responseStr = (new ResponseEntity<List<WeatherTable>>(weatherRepository.findByCityNameAndCountryName(cityName, countryName), HttpStatus.OK).getBody().toString());
//        System.out.println();
//        System.out.println("Response body: " + responseStr);
//        int begin = responseStr.indexOf("(") + 1;
//        int end = responseStr.lastIndexOf(")");
//        responseStr = responseStr.substring(begin, end);
//        System.out.println();
//        System.out.println("After substring: " + responseStr);
//        System.out.println();
//
//        String[] responseList = responseStr.split(", ");
//
//        String responseDescription = responseList[3].split("=")[1];
//        int responseTemperature = Integer.parseInt(responseList[4].split("=")[1]);
//        int responseFeelsLike = Integer.parseInt(responseList[5].split("=")[1]);
//        int responsePressure = Integer.parseInt(responseList[6].split("=")[1]);
//        int responseHumidity = Integer.parseInt(responseList[7].split("=")[1]);
//        float responseWingSpeed = Float.parseFloat(responseList[8].split("=")[1]);
//        System.out.println("After splitting:");
//        System.out.println("responseDescription: " + responseDescription);
//        System.out.println("responseTemperature: " + responseTemperature);
//        System.out.println("responseFeelsLike: " + responseFeelsLike);
//        System.out.println("responsePressure: " + responsePressure);
//        System.out.println("responseHumidity: " + responseHumidity);
//        System.out.println("responseWingSpeed: " + responseWingSpeed);
//        //--------------------------------------------------------------------------------------------


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

