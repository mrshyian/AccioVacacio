package com.codecool.travelhelper.API.simple.webclient.weather;


import com.neovisionaries.i18n.CountryCode;
import com.codecool.travelhelper.API.simple.model.WeatherDto;
import com.codecool.travelhelper.API.simple.webclient.weather.dto.OpenWeatherWeatherDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherClient {
    private RestTemplate restTemplate = new RestTemplate();
    private final static String WEATHER_URL = "https://api.openweathermap.org/data/2.5/";
    private final static String API_KEY = "90099a4459b876e68cec7c315845f4ff";

    public WeatherDto getWeatherForCity(String city) {
        OpenWeatherWeatherDto openWeatherWeatherDto =  callGetMethode("weather?appid={apiKey}&units=metric&lang=pl&q={city}",
                OpenWeatherWeatherDto.class,
                API_KEY,
                city);
        CountryCode cc = CountryCode.findByName("Poland").get(0);
        System.out.println("Input = Poland");
        System.out.println("Result = " + cc);

        return WeatherDto.builder()
                .temperature(openWeatherWeatherDto.getMain().getTemp())
                .feelsLike(openWeatherWeatherDto.getMain().getFeels_like())
                .pressure(openWeatherWeatherDto.getMain().getPressure())
                .humidity(openWeatherWeatherDto.getMain().getHumidity())
                .windSpeed(openWeatherWeatherDto.getWind().getSpeed())
                .city(openWeatherWeatherDto.getName())
                .build();
    }

    public <T> T callGetMethode(String url, Class<T> responseType, Object...objects) {
        return restTemplate.getForObject(WEATHER_URL + url, responseType, objects);
    }
}
