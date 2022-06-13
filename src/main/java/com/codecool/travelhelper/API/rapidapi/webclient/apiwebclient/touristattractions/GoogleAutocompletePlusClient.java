package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.touristattractions;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.touristattractions.GoogleAutocompletePlusDto;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClient;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class GoogleAutocompletePlusClient extends ApiWebClient {
    public GoogleAutocompletePlusClient() {
        super(ApiMetaData.GOOGLE_AUTOCOMPLETE_PLUS);
    }

    /**
     *
     * @param cityName
     * @param countryIsoCode The region from which the query is made. An ISO_3166-1 alpha-2 format. Recommended to use so that results are biased towards this region.
     * @return
     * */
    public GoogleAutocompletePlusDto getLocationCoordinates(String cityName, String countryIsoCode){
       Map<String, Object> parameters = new HashMap<>(){{
            put("query", cityName);
            put("limit", 10);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

        GoogleAutocompletePlusDto googleAutocompletePlusDto = getGoogleAutocompletePlusDto(response, countryIsoCode);

        return googleAutocompletePlusDto;
    }

    private GoogleAutocompletePlusDto getGoogleAutocompletePlusDto(JsonObject response, String countryCode){
        for (int i = 0; i < response.size(); i++) {
            GoogleAutocompletePlusDto googleAutocompletePlusDto = getSingleGoogleAutocompletePlusDto(response,countryCode, i);

            if (googleAutocompletePlusDto != null){
                return googleAutocompletePlusDto;
            }
        }

        return null;
    }

    private GoogleAutocompletePlusDto getSingleGoogleAutocompletePlusDto (JsonObject response, String countryIsoCode, int elementIndex){
        JsonObject responseValue = response.getAsJsonObject("response");

        if (isCountryMatching(responseValue, countryIsoCode, elementIndex)){
            float latitude = Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonArray("latitude", "predictions", responseValue, elementIndex));
            float longitude = Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonArray("longitude", "predictions", responseValue, elementIndex));

            GoogleAutocompletePlusDto googleAutocompletePlusDto = GoogleAutocompletePlusDto.builder()
                    .latitude(latitude)
                    .longitude(longitude)
                    .build();
            return googleAutocompletePlusDto;
        }
        return null;
    }

    private boolean isCountryMatching(JsonObject response, String countryIsoName, int elementIndex){
        String responseCountryValue = getValueByKeyFromJsonObjectInsideJsonArray("country", "predictions", response, elementIndex);

        return responseCountryValue.equals(countryIsoName);
    }
}
