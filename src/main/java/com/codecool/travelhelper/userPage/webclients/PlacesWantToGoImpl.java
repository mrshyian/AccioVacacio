package com.codecool.travelhelper.userPage.webclients;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.NoteTable;
import com.codecool.travelhelper.aws.database.models.PlacesWantToGoTable;
import com.codecool.travelhelper.aws.database.repositories.PlacesWantToGoRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.loginAndRegistration.webclients.LoginImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        String newCountry = countryJsonObject.get("country").getAsString();

        JsonObject cityJsonObject = (JsonObject)jsonParser.parse(countryAndCity);
        String newCity = cityJsonObject.get("city").getAsString();

        Optional<PlacesWantToGoTable> response = placesWantToGoRepository.findByCountryAndCityAndMyUserTableId(newCountry, newCity, userFromDB.getId());

        if (response.isPresent()){
            System.out.println("This place already been added");
        } else {
            placesWantToGoRepository.save(new PlacesWantToGoTable(
                    newCountry,
                    newCity,
                    userFromDB
            ));
        }
    }

}
