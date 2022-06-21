package com.codecool.travelhelper.aws.database.repositories.search_city_repositories;

import com.codecool.travelhelper.aws.database.tables.search_city_tables.WeatherTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public interface WeatherRepository  extends JpaRepository<WeatherTable, Long> {
//
//    List<WeatherTable> findByCityNameAndCountryName(String cityName, String countryName);
//    void setWeatherDataByCityAndCountryName(WeatherTable weatherTable);
//
//}


public interface WeatherRepository{
    void setWeatherDataByCityAndCountryName(WeatherTable weatherTable);
}

