package com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.touristattractions;

import com.codecool.travelhelper.API.rapidapi.model.apimodel.touristattractions.GoogleAutocompletePlusDto;
import com.codecool.travelhelper.API.rapidapi.model.apimodel.touristattractions.PopularAttractionDtoRapidApi;
import com.codecool.travelhelper.API.rapidapi.model.apimodel.touristattractions.TrueWayPlacesDto;
import com.codecool.travelhelper.API.rapidapi.webclient.apiwebclient.ApiWebClientRapidApi;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PopularAttractionsClientImplRapidApi extends ApiWebClientRapidApi implements PopularAttractionsClientRapidApi {

    public PopularAttractionsClientImplRapidApi() {}

    @Override
    public List<PopularAttractionDtoRapidApi> getPopularAttractions(String cityName, String countryIsoCode, int distance, int amountOfAttractions) {
        List<PopularAttractionDtoRapidApi> popularAttractionDtoRapidApiList = new ArrayList<>();

        GoogleAutocompletePlusClientRapidApi googleAutocompletePlusClient = new GoogleAutocompletePlusClientRapidApi();
        GoogleAutocompletePlusDto googleAutocompletePlusDto = googleAutocompletePlusClient.getLocationCoordinates(cityName, countryIsoCode);

        float latitude = googleAutocompletePlusDto.getLatitude();
        float longitude = googleAutocompletePlusDto.getLongitude();

        TrueWayPlacesClientRapidApi trueWayPlacesClient = new TrueWayPlacesClientRapidApi();
        List<TrueWayPlacesDto> trueWayPlacesDtoList = trueWayPlacesClient.getLocationData(latitude, longitude, distance, amountOfAttractions);

        for (TrueWayPlacesDto place: trueWayPlacesDtoList) {
            PopularAttractionDtoRapidApi attractionDto = getSinglePopularAttractionsDto(place);
            popularAttractionDtoRapidApiList.add(attractionDto);
        }

        return popularAttractionDtoRapidApiList;
    }

    private PopularAttractionDtoRapidApi getSinglePopularAttractionsDto(TrueWayPlacesDto trueWayPlacesDtoList){
        String locationName = trueWayPlacesDtoList.getLocationName();
        String locationAddress = trueWayPlacesDtoList.getLocationAddress();
        int distanceToLocation = trueWayPlacesDtoList.getDistanceToLocation();
        String website = trueWayPlacesDtoList.getWebsite();

        PopularAttractionDtoRapidApi popularAttractionsDto = PopularAttractionDtoRapidApi.builder()
                .locationName(locationName)
                .locationAddress(locationAddress)
                .distanceToLocation(distanceToLocation)
                .website(website)
                .build();

        return popularAttractionsDto;
    }
}
