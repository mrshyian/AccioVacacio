package com.codecool.travelhelper.API.simple.webclient;


import com.codecool.travelhelper.API.simple.models.AirportsIataCodeModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AirportsIataCodeClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final static String AIRPORT_INFO_URL = "https://api.travelpayouts.com/data/en/cities.json";


    public AirportsIataCodeModel getAirportIataCode(String cityName, String countryIsoCode) {
        String response = restTemplate.getForObject(AIRPORT_INFO_URL, String.class);

        JsonArray jsonArrayResponse = new JsonParser().parse(response).getAsJsonArray();

        int responseSize = jsonArrayResponse.size();

        for (int i = 0; i < responseSize; i++) {
            JsonObject innerJsonObject = jsonArrayResponse.get(i).getAsJsonObject();

            if(isCountryMatching(innerJsonObject, countryIsoCode)){
                if (isCityMatching(innerJsonObject, cityName)){
                    return getAirportsIataCodeModel(innerJsonObject);
                }
            }
        }

        return mockData();
    }

    private AirportsIataCodeModel mockData(){
        return AirportsIataCodeModel.builder()
                .airportIataCode("WAW")
                .latitude((float) 52.170906)
                .longitude((float) 20.97329)
                .build();
    }

    private boolean isCountryMatching(JsonObject jsonObject, String countryIsoCode){
        JsonElement responseCountryCodeIso = jsonObject.get("country_code");
        return  responseCountryCodeIso.getAsString().equals(countryIsoCode.toUpperCase());
    }
    private boolean isCityMatching(JsonObject jsonObject, String cityName){
        JsonElement responseCountryCodeIso = jsonObject.get("name_translations").getAsJsonObject().get("en");
        return  (responseCountryCodeIso.getAsString().toUpperCase()).equals(cityName.toUpperCase());
    }


    /**
     * response from API is going to be as:
     *      *      [{
     *      *             "country_code":"PL",
     *      *             "code":"KRK",
     *      *             "coordinates":{"lat":50.075493,"lon":19.793743},
     *      *             "name":"Krakow",
     *      *             "time_zone":"Europe/Warsaw",
     *      *             "name_translations":{"en":"Krakow"},
     *      *             "cases":{"su":"Krakow"}
     *      *           },...]
     * @param jsonObject
     * @return
     */
    private AirportsIataCodeModel getAirportsIataCodeModel(JsonObject jsonObject){
        String airportIataCode = jsonObject.get("code").getAsString();
        float latitude = Float.parseFloat(jsonObject.get("coordinates").getAsJsonObject().get("lat").getAsString());
        float longitude = Float.parseFloat(jsonObject.get("coordinates").getAsJsonObject().get("lon").getAsString());

        return AirportsIataCodeModel.builder()
                .airportIataCode(airportIataCode)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}

