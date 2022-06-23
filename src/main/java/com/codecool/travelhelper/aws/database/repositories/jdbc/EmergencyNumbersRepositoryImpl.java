package com.codecool.travelhelper.aws.database.repositories.jdbc;

import com.codecool.travelhelper.aws.database.models.EmergencyNumbersTable;
import com.codecool.travelhelper.aws.database.models.WeatherTable;
import com.codecool.travelhelper.aws.database.repositories.EmergencyNumbersRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NoArgsConstructor
@Getter
public class EmergencyNumbersRepositoryImpl {

    @Autowired
    EmergencyNumbersRepository emergencyNumbersRepository;

    public void setEmergencyNumbersDataByCityAndCountryName(EmergencyNumbersTable emergencyNumbersTable) {
        EmergencyNumbersTable updatedResponseObject;
        String cityName = emergencyNumbersTable.getCityName();
        String countryName = emergencyNumbersTable.getCountryName();

        Optional<EmergencyNumbersTable> response = emergencyNumbersRepository.findAllByCityNameAndCountryName(cityName, countryName);

        if (response.isPresent()){
            updatedResponseObject = response.get();
            updateResponseObject(emergencyNumbersTable, updatedResponseObject);
        } else {
            updatedResponseObject = emergencyNumbersTable;
        }

        emergencyNumbersRepository.save(updatedResponseObject);
    }

    private void updateResponseObject(EmergencyNumbersTable newObject, EmergencyNumbersTable objectFromDB){
        objectFromDB.setAmbulance(newObject.getAmbulance());
        objectFromDB.setPolice(newObject.getPolice());
        objectFromDB.setFireGuard(newObject.getFireGuard());
        objectFromDB.setDispatch(newObject.getDispatch());


    }
}
