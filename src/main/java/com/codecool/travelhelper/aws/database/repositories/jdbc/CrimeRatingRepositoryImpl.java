package com.codecool.travelhelper.aws.database.repositories.jdbc;

import com.codecool.travelhelper.aws.database.models.CrimeRatingTable;
import com.codecool.travelhelper.aws.database.repositories.CrimeRatingRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NoArgsConstructor
@Getter
public class CrimeRatingRepositoryImpl {

    @Autowired
    CrimeRatingRepository crimeRatingRepository;

    public void setCrimeRatingDataByCityAndCountryName(CrimeRatingTable crimeRatingTable) {
        CrimeRatingTable updatedResponseObject;
        String cityName = crimeRatingTable.getCityName();
        String countryName = crimeRatingTable.getCountryName();

        Optional<CrimeRatingTable> response = crimeRatingRepository.findAllByCityNameAndCountryName(cityName, countryName);

        if (response.isPresent()){
            updatedResponseObject = response.get();
            updateResponseObject(crimeRatingTable, updatedResponseObject);
        } else {
            updatedResponseObject = crimeRatingTable;
        }

        crimeRatingRepository.save(updatedResponseObject);
    }

    private void updateResponseObject(CrimeRatingTable newObject, CrimeRatingTable objectFromDB){
        objectFromDB.setStarCount(newObject.getStarCount());
    }

}
