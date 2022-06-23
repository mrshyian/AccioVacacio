package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.WeatherTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
//public interface WeatherRepository  extends JpaRepository<WeatherTable, Long> {
//
//    List<WeatherTable> findByCityNameAndCountryName(String cityName, String countryName);
//    void setWeatherDataByCityAndCountryName(WeatherTable weatherTable);
//
//}


public interface WeatherRepository  extends JpaRepository<WeatherTable, Long>{

    Optional<WeatherTable> findAllByCityNameAndCountryName(String cityName, String countryName);


}

