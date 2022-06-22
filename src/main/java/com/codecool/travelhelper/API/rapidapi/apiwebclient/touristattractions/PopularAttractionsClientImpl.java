package com.codecool.travelhelper.API.rapidapi.apiwebclient.touristattractions;

import com.codecool.travelhelper.API.rapidapi.apimodel.touristattractions.GoogleAutocompletePlusDto;
import com.codecool.travelhelper.API.rapidapi.apimodel.touristattractions.PopularAttractionDto;
import com.codecool.travelhelper.API.rapidapi.apimodel.touristattractions.TrueWayPlacesDto;
import com.codecool.travelhelper.API.rapidapi.apiwebclient.ApiWebClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PopularAttractionsClientImpl extends ApiWebClient implements PopularAttractionsClient {

    public PopularAttractionsClientImpl() {}

    @Override
    public List<PopularAttractionDto> getPopularAttractions(String cityName, String countryIsoCode, int distance, int amountOfAttractions) {
        List<PopularAttractionDto> popularAttractionDtoRapidApiList = new ArrayList<>();

        GoogleAutocompletePlusClient googleAutocompletePlusClient = new GoogleAutocompletePlusClient();
        GoogleAutocompletePlusDto googleAutocompletePlusDto = googleAutocompletePlusClient.getLocationCoordinates(cityName, countryIsoCode);

        float latitude = googleAutocompletePlusDto.getLatitude();
        float longitude = googleAutocompletePlusDto.getLongitude();

        TrueWayPlacesClient trueWayPlacesClient = new TrueWayPlacesClient();
        List<TrueWayPlacesDto> trueWayPlacesDtoList = trueWayPlacesClient.getLocationData(latitude, longitude, distance, amountOfAttractions);

        for (TrueWayPlacesDto place: trueWayPlacesDtoList) {
            PopularAttractionDto attractionDto = getSinglePopularAttractionsDto(place);
            popularAttractionDtoRapidApiList.add(attractionDto);
        }

        return popularAttractionDtoRapidApiList;
    }

    private PopularAttractionDto getSinglePopularAttractionsDto(TrueWayPlacesDto trueWayPlacesDtoList){
        String locationName = trueWayPlacesDtoList.getLocationName();
        String locationAddress = trueWayPlacesDtoList.getLocationAddress();
        int distanceToLocation = trueWayPlacesDtoList.getDistanceToLocation();
        String website = trueWayPlacesDtoList.getWebsite();

        PopularAttractionDto popularAttractionsDto = PopularAttractionDto.builder()
                .locationName(locationName)
                .locationAddress(locationAddress)
                .distanceToLocation(distanceToLocation)
                .website(website)
                .build();

        return popularAttractionsDto;
    }
}
