package com.codecool.travelhelper.aws.database.models.repositories;

import com.codecool.travelhelper.aws.database.models.WeatherTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface WeatherRepository  extends JpaRepository<WeatherTable, Long>{

    Optional<WeatherTable> findAllByCityNameAndCountryName(String cityName, String countryName);

    void deleteByCityNameAndCountryName(String cityName, String countryName);
}

