package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.AirportDetailsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportDetailsRepository extends JpaRepository<AirportDetailsTable, Long>{
    Optional<AirportDetailsTable> findAllByCityNameAndCountryName(String cityName, String countryName);

    void deleteByCityNameAndCountryName(String cityName, String countryName);

}
