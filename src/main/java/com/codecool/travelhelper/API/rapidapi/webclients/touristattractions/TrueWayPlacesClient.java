package com.codecool.travelhelper.API.rapidapi.webclients.touristattractions;

import com.codecool.travelhelper.API.rapidapi.models.touristattractions.TrueWayPlacesDto;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiWebClient;
import com.codecool.travelhelper.API.rapidapi.webclients.ApiMetaData;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrueWayPlacesClient extends ApiWebClient {
    public TrueWayPlacesClient() {
        super(ApiMetaData.TRUE_WAY_PLACES);
    }

    /**
     *
     * @param latitude
     * @param longitude
     * @param radius The distance (in meters) within which to return results. Max = 10000 m
     * @param howMuchRecordsNeeded
     * @return TrueWayPlacesDto
     */
    public List<TrueWayPlacesDto> getLocationData(float latitude, float longitude, int radius, int howMuchRecordsNeeded){
        String location = latitude + "," + longitude;
        Map<String, Object> parameters = new HashMap<>(){{
            put("location", location);
            put("type", "tourist_attraction");
            put("radius", radius);
        }};
        this.setParameters(parameters);

        JsonObject response = getApiResponse(this.getUrl(), this.getHeadersData(), this.getParameters());

        List<TrueWayPlacesDto> trueWayPlacesDto = getTrueWayPlacesDtoList(response, howMuchRecordsNeeded);

        return trueWayPlacesDto;
    }

    private List<TrueWayPlacesDto> getTrueWayPlacesDtoList(JsonObject response, int howMuchRecordsNeeded){
        List<TrueWayPlacesDto> trueWayPlacesDtoList = new ArrayList<>();

        int responseSize = response.getAsJsonArray("results").size();
        int recordsNeeded = Math.min(howMuchRecordsNeeded, responseSize);

              for (int i = 0; i < responseSize; i++) {
                  if (trueWayPlacesDtoList.size() < recordsNeeded){

                      if (hasObjectWebsite(response, i)){
                          TrueWayPlacesDto trueWayPlacesDto = getSingleTrueWayPlacesDto(response, i);
                          trueWayPlacesDtoList.add(trueWayPlacesDto);
                      }
                  }
              }

        return trueWayPlacesDtoList;
    }

    private TrueWayPlacesDto getSingleTrueWayPlacesDto (JsonObject response, int elementIndex){
        String locationName = getValueByKeyFromJsonObjectInsideJsonArray("name", "results", response, elementIndex);
        String locationAddress = getValueByKeyFromJsonObjectInsideJsonArray("address", "results", response, elementIndex);
        int distanceToLocation = Integer.parseInt(getValueByKeyFromJsonObjectInsideJsonArray("distance", "results", response, elementIndex));

        String website = getValueByKeyFromJsonObjectInsideJsonArray("website", "results", response, elementIndex);

        TrueWayPlacesDto trueWayPlacesDto = TrueWayPlacesDto.builder()
                .locationName(locationName)
                .locationAddress(locationAddress)
                .distanceToLocation(distanceToLocation)
                .website(website)
                .build();

        return trueWayPlacesDto;
    }

    private boolean hasObjectWebsite(JsonObject response, int elementIndex){
        try{
            getValueByKeyFromJsonObjectInsideJsonArray("website", "results", response, elementIndex);
        } catch (NullPointerException e){
            return false;
        }
        return true;
    }
}
