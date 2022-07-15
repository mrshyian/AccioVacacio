package com.codecool.travelhelper.aws.database.repositories.jdbc;

import com.codecool.travelhelper.aws.database.models.WeatherTable;
import com.codecool.travelhelper.aws.database.repositories.WeatherRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NoArgsConstructor
@Getter
public class WeatherRepositoryImpl{

    @Autowired
    private WeatherRepository weatherRepository;


    public void setWeatherDataByCityAndCountryName(WeatherTable weatherTable) {
        WeatherTable updatedResponseObject;
        String cityName = weatherTable.getCityName();
        String countryName = weatherTable.getCountryName();

        Optional<WeatherTable> response = weatherRepository.findAllByCityNameAndCountryName(cityName, countryName);

        if (response.isPresent()){
            updatedResponseObject = response.get();
            updateResponseObject(weatherTable, updatedResponseObject);
        } else {
            updatedResponseObject = weatherTable;
        }

        weatherRepository.save(updatedResponseObject);
    }

    private void updateResponseObject(WeatherTable newObject, WeatherTable objectFromDB){
        objectFromDB.setDescription(newObject.getDescription());
        objectFromDB.setHumidity(newObject.getHumidity());
        objectFromDB.setFeelsLike(newObject.getFeelsLike());
        objectFromDB.setPressure(newObject.getPressure());
        objectFromDB.setTemperature(newObject.getTemperature());
        objectFromDB.setWingSpeed(newObject.getWingSpeed());

    }

}
