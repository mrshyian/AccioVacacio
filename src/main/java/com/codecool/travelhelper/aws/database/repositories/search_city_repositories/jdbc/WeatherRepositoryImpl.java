package com.codecool.travelhelper.aws.database.repositories.search_city_repositories.jdbc;

import com.codecool.travelhelper.aws.database.repositories.search_city_repositories.WeatherRepository;
import com.codecool.travelhelper.aws.database.tables.search_city_tables.WeatherTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Component
@NoArgsConstructor
@Getter
public class WeatherRepositoryImpl{

    @Autowired
    WeatherRepository weatherRepository;


    public void setWeatherDataByCityAndCountryName(WeatherTable weatherTable) {
        String cityName = weatherTable.getCityName();
        String countryName = weatherTable.getCountryName();

        Optional<WeatherTable> response = weatherRepository.findAllByCityNameAndCountryName(cityName, countryName);

        if (response.isPresent()){
            WeatherTable newsWeather = response.get();

        }

        System.out.println(response.toString());
    }

}
