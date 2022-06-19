package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.touristattractions;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.touristattractions.GoogleAutocompletePlusDto;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiMetaData;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClient;
import com.google.gson.JsonArray;
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
        String queryContent = cityName + " " + countryIsoCode;

        Map<String, Object> parameters = new HashMap<>(){{
            put("query", queryContent);
            put("limit", 10);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

        GoogleAutocompletePlusDto googleAutocompletePlusDto = getGoogleAutocompletePlusDto(response, countryIsoCode);

        return googleAutocompletePlusDto;
    }

    private GoogleAutocompletePlusDto getGoogleAutocompletePlusDto(JsonObject response, String countryCode){
        JsonArray responseValue = (response.getAsJsonObject("response")).getAsJsonArray("predictions");

        for (int i = 0; i < responseValue.size(); i++) {
            GoogleAutocompletePlusDto googleAutocompletePlusDto = getCountryMatchedGoogleAutocompletePlusDto(response,countryCode, i);

            if (googleAutocompletePlusDto != null){
                return googleAutocompletePlusDto;
            }
        }

        return getFirstNotNullGoogleAutocompletePlusDto(response, countryCode);
    }
    
    private GoogleAutocompletePlusDto getFirstNotNullGoogleAutocompletePlusDto(JsonObject response, String countryCode){
        JsonArray responseValue = (response.getAsJsonObject("response")).getAsJsonArray("predictions");

        for (int i = 0; i < responseValue.size(); i++) {
            GoogleAutocompletePlusDto googleAutocompletePlusDto = getGoogleAutocompletePlusDto(response, i);

            if (googleAutocompletePlusDto != null){
                return googleAutocompletePlusDto;
            }
        }

        return null;
    }

    private GoogleAutocompletePlusDto getCountryMatchedGoogleAutocompletePlusDto(JsonObject response, String countryIsoCode, int elementIndex){
        JsonObject responseValue = response.getAsJsonObject("response");

        if (isCountryMatching(responseValue, countryIsoCode, elementIndex)){
            return getGoogleAutocompletePlusDto(responseValue, elementIndex);
        }

        return null;
    }

    private GoogleAutocompletePlusDto getGoogleAutocompletePlusDto(JsonObject response, int elementIndex){
        float latitude = Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonArray("latitude", "predictions", response, elementIndex));
        float longitude = Float.parseFloat(getValueByKeyFromJsonObjectInsideJsonArray("longitude", "predictions", response, elementIndex));

        GoogleAutocompletePlusDto googleAutocompletePlusDto = GoogleAutocompletePlusDto.builder()
                .latitude(latitude)
                .longitude(longitude)
                .build();
        return googleAutocompletePlusDto;
    }

    private boolean isCountryMatching(JsonObject response, String countryIsoName, int elementIndex){
        String responseCountryValue = getValueByKeyFromJsonObjectInsideJsonArray("country", "predictions", response, elementIndex);

        if (responseCountryValue != null){
            return responseCountryValue.equals(countryIsoName);
        }

        return false;
    }
}
