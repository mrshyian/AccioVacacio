package com.codecool.travelhelper.aws.database.repositories.jdbc;

import com.codecool.travelhelper.aws.database.models.EmergencyNumbersTable;
import com.codecool.travelhelper.aws.database.models.LivingCostsTable;
import com.codecool.travelhelper.aws.database.repositories.LivingCostsRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NoArgsConstructor
@Getter
public class LivingCostsRepositoryImpl {

    @Autowired
    LivingCostsRepository livingCostsRepository;


    public void setLivingCostsDataByCityAndCountryNameAndItemName(LivingCostsTable livingCostsTable) {
        LivingCostsTable updatedResponseObject;
        String cityName = livingCostsTable.getCityName();
        String countryName = livingCostsTable.getCountryName();
        String itemName = livingCostsTable.getItemName();

        Optional<LivingCostsTable> response = livingCostsRepository.findAllByCityNameAndCountryNameAndItemName(cityName, countryName, itemName);

        if (response.isPresent()){
            updatedResponseObject = response.get();
            updateResponseObject(livingCostsTable, updatedResponseObject);
        } else {
            updatedResponseObject = livingCostsTable;
        }

        livingCostsRepository.save(updatedResponseObject);
    }

    private void updateResponseObject(LivingCostsTable newObject, LivingCostsTable objectFromDB){
        objectFromDB.setAveragePrice(newObject.getAveragePrice());
        objectFromDB.setCurrency(newObject.getCurrency());
    }
}
