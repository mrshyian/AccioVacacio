package com.codecool.travelhelper.aws.database.repositories.jdbc;

import com.codecool.travelhelper.aws.database.models.AirportDetailsTable;
import com.codecool.travelhelper.aws.database.repositories.AirportDetailsRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NoArgsConstructor
@Getter
public class AirportDetailsRepositoryImpl {

    @Autowired
    private AirportDetailsRepository airportDetailsRepository;

    public void setAirportDetailsDataByCityAndCountryName(AirportDetailsTable airportDetailsTable) {
        AirportDetailsTable updatedResponseObject;
        String cityName = airportDetailsTable.getCityName();
        String countryName = airportDetailsTable.getCountryName();

        Optional<AirportDetailsTable> response = airportDetailsRepository.findAllByCityNameAndCountryName(cityName, countryName);

        if (response.isPresent()){
            updatedResponseObject = response.get();
            updateResponseObject(airportDetailsTable, updatedResponseObject);
        } else {
            updatedResponseObject = airportDetailsTable;
        }

        airportDetailsRepository.save(updatedResponseObject);
    }

    private void updateResponseObject(AirportDetailsTable newObject, AirportDetailsTable objectFromDB){
        objectFromDB.setAirportName(newObject.getAirportName());
        objectFromDB.setAirportLocation(newObject.getAirportLocation());
        objectFromDB.setAirportStreetName(newObject.getAirportStreetName());
        objectFromDB.setAirportStreetNumber(newObject.getAirportStreetNumber());
        objectFromDB.setAirportState(newObject.getAirportState());
        objectFromDB.setAirportCity(newObject.getAirportCity());
        objectFromDB.setAirportPhoneNumber(newObject.getAirportPhoneNumber());
        objectFromDB.setAirportWebsite(newObject.getAirportWebsite());

    }
}
