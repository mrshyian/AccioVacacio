package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.EmergencyNumbersTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmergencyNumbersRepository  extends JpaRepository<EmergencyNumbersTable, Long> {
    Optional<EmergencyNumbersTable> findAllByCityNameAndCountryName(String cityName, String countryName);

    void deleteByCityNameAndCountryName(String cityName, String countryName);
}
