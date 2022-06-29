package com.codecool.travelhelper.userPage.webclients;

import com.codecool.travelhelper.API.rapidapi.models.BingImageSearch;
import com.codecool.travelhelper.API.rapidapi.webclients.bingimagesearch.BingImageSearchClientImpl;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.PlacesWantToGoTable;
import com.codecool.travelhelper.aws.database.repositories.PlacesWantToGoRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.loginAndRegistration.webclients.LoginImpl;
import com.codecool.travelhelper.userPage.models.PlaceWantToGoModel;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PlacesWantToGoImpl {

    @Autowired
    PlacesWantToGoRepository placesWantToGoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginImpl loginImpl;

    public void addNewPlace(String countryAndCity){

        Long userId = loginImpl.getCurrentUserId();
        MyUserTable userFromDB = userRepository.findAllById(userId);

        JsonParser jsonParser = new JsonParser();

        JsonObject countryJsonObject = (JsonObject)jsonParser.parse(countryAndCity);
        String country = countryJsonObject.get("country").getAsString();

        JsonObject cityJsonObject = (JsonObject)jsonParser.parse(countryAndCity);
        String city = cityJsonObject.get("city").getAsString();

        BingImageSearchClientImpl imagesUrlClient= new BingImageSearchClientImpl();
        BingImageSearch imagesUrl = imagesUrlClient.getImagesUrl(country + " " + city, 3);

        Optional<PlacesWantToGoTable> response = placesWantToGoRepository.findByCountryAndCityAndMyUserTableId(country, city, userFromDB.getId());

        if (response.isPresent()){
            System.out.println("This place already been added");
        } else {
            placesWantToGoRepository.save(new PlacesWantToGoTable(
                    country,
                    city,
                    userFromDB
            ));
        }
    }

    public List<PlaceWantToGoModel> getPlacesFromDB(){

        Long userId = loginImpl.getCurrentUserId();

        List<PlacesWantToGoTable> placesList =  placesWantToGoRepository.findAllByMyUserTableId(userId);

        List<PlaceWantToGoModel> placesListWithUrl = new ArrayList<>();

        for (PlacesWantToGoTable singlePlace: placesList) {
            placesListWithUrl.add(placeBuilder(singlePlace));
        }
        return placesListWithUrl;
    }

    public PlaceWantToGoModel placeBuilder(PlacesWantToGoTable placeWantToGo){

        BingImageSearchClientImpl imagesUrlClient= new BingImageSearchClientImpl();
        BingImageSearch imagesUrl = imagesUrlClient.getImagesUrl(
                placeWantToGo.getCountry() + " " + placeWantToGo.getCity(),
                3
        );

        return PlaceWantToGoModel.builder()
                .placesWantToGo(placeWantToGo)
                .imagesUrl(imagesUrl)
                .build();
    }



}
