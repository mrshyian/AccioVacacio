package com.codecool.travelhelper.aws.database.models.repositories;

import com.codecool.travelhelper.aws.database.models.LivingCostsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface LivingCostsRepository  extends JpaRepository<LivingCostsTable, Long> {

    Optional<LivingCostsTable> findAllByCityNameAndCountryNameAndItemName(String cityName, String countryName, String itemName);

    void deleteAllByCityNameAndCountryName(String cityName, String countryName);
}
