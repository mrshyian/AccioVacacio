package com.codecool.travelhelper.userPage.models;



import com.codecool.travelhelper.API.rapidapi.models.BingImageSearch;
import com.codecool.travelhelper.aws.database.models.PlacesWantToGoTable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PlaceWantToGoModel {

    private BingImageSearch imagesUrl;
    private PlacesWantToGoTable placesWantToGo;

}
