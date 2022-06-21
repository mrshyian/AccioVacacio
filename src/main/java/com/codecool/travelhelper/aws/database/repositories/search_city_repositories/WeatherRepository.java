package com.codecool.travelhelper.aws.database.repositories.search_city_repositories;

import com.codecool.travelhelper.aws.database.tables.search_city_tables.CrimeRatingTable;
import com.codecool.travelhelper.aws.database.tables.search_city_tables.WeatherTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface WeatherRepository  extends JpaRepository<WeatherTable, Long> {

    List<WeatherTable> findByCityNameAndCountryName(String cityName, String countryName);

    @Modifying
    @Query(nativeQuery = true, value =
            "insert in WeatherTable (cityName, countryName, description, temperature, feelsLike, pressure, humidity, wingSpeed) " +
            "values (:cityName, :countryName, :description, :temperature, :feelsLike, :pressure, :humidity, :wingSpeed)" +
            "on duplicate key update " +
                    "description=:description, " +
                    "temperature=:temperature, " +
                    "feelsLike=:feelsLike, " +
                    "pressure=:pressure, " +
                    "humidity=:humidity, " +
                    "wingSpeed=:wingSpeed")
    void setWeatherDataByCityAndCountryName(@Param("description") String description,
                                            @Param("temperature") int temperature,
                                            @Param("feelsLike") int feelsLike,
                                            @Param("pressure") int pressure,
                                            @Param("humidity") int humidity,
                                            @Param("wingSpeed") float wingSpeed,
                                            @Param("cityName") String cityName,
                                            @Param("countryName") String countryName);


}
