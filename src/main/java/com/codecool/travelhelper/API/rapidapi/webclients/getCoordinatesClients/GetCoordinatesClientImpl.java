package com.codecool.travelhelper.API.rapidapi.webclients.getCoordinatesClients;

import com.codecool.travelhelper.API.rapidapi.models.GetCoordinatesModel;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiWebClient;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GetCoordinatesClientImpl extends ApiWebClient{

    public GetCoordinatesClientImpl() {
        super(ApiMetaData.WEATHER);
    }

    public GetCoordinatesModel getCityCoordinates(String cityName){
        Map<String, Object> parameters = new HashMap<>(){{
            put("q", cityName);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

        return getGetCoordinatesDto(response);
    }

    public GetCoordinatesModel getGetCoordinatesDto(JsonObject response) {
        float longitude = Float.parseFloat(response.getAsJsonObject().get("coord").getAsJsonObject().get("lon").getAsString());
        float latitude = Float.parseFloat(response.getAsJsonObject().get("coord").getAsJsonObject().get("lat").getAsString());


        return GetCoordinatesModel.builder()
                .lat(latitude)
                .lon(longitude)
                .build();
    }
}
